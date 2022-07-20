package ru.ozon.route256.featureproductsimpl.domain.interactor

import io.reactivex.Observable
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.featureproductsimpl.domain.model.IsInCartState
import ru.ozon.route256.featureproductsimpl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val repository: ProductsRepository
) : ProductsInteractor {

    override fun getObservable(): Observable<List<ProductDTO>> =
        repository.getObservable()

    override fun inCartClick(guid: String): Observable<IsInCartState> =
        repository.isInCartState(guid)

    override fun getCart(): List<InCartModel>? =
        repository.getCart()

    override fun successWorkersCompletion(
        productsDTOResponse: String,
        productsDetailPageDTOResponse: String
    ) {
        repository.successWorkersCompletion(productsDTOResponse, productsDetailPageDTOResponse)
    }

    override fun failureWorkersCompletion() {
        repository.failureWorkersCompletion()
    }
}
