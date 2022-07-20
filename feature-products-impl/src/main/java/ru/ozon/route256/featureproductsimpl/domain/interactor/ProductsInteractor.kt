package ru.ozon.route256.featureproductsimpl.domain.interactor

import io.reactivex.Observable
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.featureproductsimpl.domain.model.IsInCartState

interface ProductsInteractor {
    fun successWorkersCompletion(productsDTOResponse: String, productsDetailPageDTOResponse: String)
    fun failureWorkersCompletion()
    fun getObservable(): Observable<List<ProductDTO>>
    fun inCartClick(guid: String): Observable<IsInCartState>
    fun getCart(): List<InCartModel>?
}
