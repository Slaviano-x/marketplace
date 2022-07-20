package ru.ozon.route256.featureproductsimpl.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.ozon.route256.coredataapi.ResourceProvider
import ru.ozon.route256.featureproductsimpl.R
import ru.ozon.route256.featureproductsimpl.domain.interactor.ProductsInteractor
import ru.ozon.route256.featureproductsimpl.domain.mapper.toVO
import ru.ozon.route256.featureproductsimpl.domain.model.IsInCartState
import ru.ozon.route256.featureproductsimpl.domain.model.ProductsQueryState
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.ProductVO
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.TitleVO
import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel
import ru.ozon.route256.workmanagerapi.AsyncProductsLoader
import ru.ozon.route256.workmanagerapi.WorkerKey
import java.util.concurrent.TimeUnit

class ProductsViewModel(
    private val interactor: ProductsInteractor,
    private val asyncProductsLoader: AsyncProductsLoader,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var uiState = MutableLiveData<ProductsQueryState>()
    private var currentList = listOf<ProductVO>()

    init {
        tryLoadData()
    }

    fun tryLoadData() {
        uiState.postValue(ProductsQueryState.Loading)
        Handler(Looper.getMainLooper()).post {
            asyncProductsLoader.loadProductsAsync().observeForever {
                if (it == null) return@observeForever
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        val productDetailsDTO =
                            it.outputData.getString(WorkerKey.KEY_RESPONSE_PRODUCTS_DETAIL_PAGE_DTO)
                                ?: return@observeForever
                        val productsDTO =
                            it.outputData.getString(WorkerKey.KEY_RESPONSE_PRODUCTS_DTO)
                                ?: return@observeForever

                        interactor.successWorkersCompletion(productsDTO, productDetailsDTO)
                    }
                    WorkInfo.State.FAILED -> {
                        interactor.failureWorkersCompletion()
                    }
                    else -> {}
                }
            }
        }
    }

    fun observeData() = interactor.getObservable()
        .map { productList ->
            productList.map { product ->
                product.toVO()
            }
        }
        .map { productList ->
            currentList = productList
            ProductsQueryState.Success(
                divideProducts(productList)
            )
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError {
            uiState.postValue(ProductsQueryState.Error)
        }
        .subscribeBy(
            onNext = { uiState.postValue(it) },
            onError = { uiState.postValue(ProductsQueryState.Error) }
        )

    fun startPeriodicUpdateData(): Disposable =
        Observable.interval(5, TimeUnit.MINUTES)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                tryLoadData()
            }
            .subscribe()

    fun inCartClick(guid: String): Disposable =
        interactor.inCartClick(guid)
            .subscribe { isInCartState ->
                currentList.find { productVO -> productVO.guid == guid }?.isInCart =
                    (isInCartState as? IsInCartState.Success)?.isInCart == true
            }

    fun updateCart() {
        val cartList = interactor.getCart()
        if (cartList != null) {
            val listCartGuid = cartList.map { it.guid }
            currentList.forEach { productVO ->
                if (listCartGuid.contains(productVO.guid)) {
                    val productInCart = cartList.find { inCartModel ->
                        inCartModel.guid == productVO.guid
                    }
                    if (productInCart != null) {
                        val isInCart = productInCart.count > 0
                        productVO.isInCart = isInCart
                    }
                }
            }
        }
    }

    private fun divideProducts(
        products: List<ProductVO>,
    ): List<ViewHolderModel> {
        return if (products.isNotEmpty()) {
            val viewHolderList = mutableListOf<ViewHolderModel>()
            val productsBeforeOneHundred = products.filter { it.price.toInt() <= 100 }
            val productsAfterOneHundred = products.filter { it.price.toInt() > 100 }

            if (productsBeforeOneHundred.isNotEmpty()) {
                viewHolderList.add(TitleVO(resourceProvider.getString(R.string.less_than_100)))
                viewHolderList.addAll(productsBeforeOneHundred)
            }

            if (productsAfterOneHundred.isNotEmpty()) {
                viewHolderList.add(TitleVO(resourceProvider.getString(R.string.more_than_100)))
                viewHolderList.addAll(productsAfterOneHundred)
            }
            return viewHolderList
        } else {
            emptyList()
        }
    }
}
