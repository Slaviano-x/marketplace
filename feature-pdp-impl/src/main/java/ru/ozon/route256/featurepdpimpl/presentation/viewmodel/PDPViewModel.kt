package ru.ozon.route256.featurepdpimpl.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.featurepdpimpl.domain.interactor.PDPInteractor
import ru.ozon.route256.featurepdpimpl.domain.mapper.toVO
import ru.ozon.route256.featurepdpimpl.domain.model.ProductDetailPageQueryState
import javax.inject.Inject

class PDPViewModel @Inject constructor(
    private val interactor: PDPInteractor
) : ViewModel() {

    private val pdpMLD = MutableLiveData<ProductDetailPageQueryState>()
    val pdpLD: LiveData<ProductDetailPageQueryState> = pdpMLD

    private var guid: String? = null
    private val cartList: List<InCartModel>? = interactor.getCart()

    fun loadData() = interactor.getProductsDetailPageDTO()
        .toObservable()
        .doOnSubscribe {
            pdpMLD.postValue(ProductDetailPageQueryState.Loading)
        }
        .flatMap { Observable.fromIterable(it) }
        .filter { it.guid == guid }
        .map { productDetailPageDTO ->
            productDetailPageDTO.count = 0
            val productInCart = cartList?.find { it.guid == productDetailPageDTO.guid }
            if (cartList != null && productInCart != null) {
                productDetailPageDTO.count = productInCart.count
            }
            productDetailPageDTO.toVO()
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError {
            pdpMLD.postValue(ProductDetailPageQueryState.Error)
        }
        .subscribeBy(
            onNext = { pdpMLD.postValue(ProductDetailPageQueryState.Success(it)) },
            onError = { pdpMLD.postValue(ProductDetailPageQueryState.Error) }
        )

    fun setProductId(value: String?) {
        this.guid = value
    }

    fun changeCount(lastValue: Int, guid: String?) {
        interactor.changeValue(lastValue, guid)
    }
}
