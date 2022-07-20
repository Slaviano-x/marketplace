package ru.ozon.route256.featureproductsimpl.data.repositoryimpl

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.featureproductsimpl.data.SharedPreferencesManager
import ru.ozon.route256.featureproductsimpl.domain.model.IsInCartState
import ru.ozon.route256.featureproductsimpl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
) : ProductsRepository {

    private var subject: PublishSubject<List<ProductDTO>> = PublishSubject.create()

    override fun getObservable(): Observable<List<ProductDTO>> {
        return subject
    }

    override fun isInCartState(guid: String): Observable<IsInCartState> =
        sharedPreferencesManager.addOrRemoveFromCart(guid)

    override fun getCart(): List<InCartModel>? =
        sharedPreferencesManager.getCart()

    override fun successWorkersCompletion(
        productsDTOResponse: String,
        productsDetailPageDTOResponse: String
    ) {
        val actualList = sharedPreferencesManager.refreshCachedData(
            productsDTOResponse,
            productsDetailPageDTOResponse
        )
        subject.onNext(actualList)
    }

    override fun failureWorkersCompletion() {
        val cachedData = sharedPreferencesManager.getProductsDTOList()

        if (cachedData != null) {
            subject.onNext(cachedData)
        }
    }
}
