package ru.ozon.route256.featurepdpimpl.domain.interactor

import io.reactivex.Single
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.featurepdpimpl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(
    private val repository: PDPRepository
) : PDPInteractor {

    override fun getProductsDetailPageDTO(): Single<List<ProductDetailPageDTO>> {
        return repository.getProductsDetailPageDTO()
    }

    override fun changeValue(lastValue: Int, guid: String?) {
        repository.changeValue(lastValue, guid)
    }

    override fun getCart(): List<InCartModel>? =
        repository.getCart()
}
