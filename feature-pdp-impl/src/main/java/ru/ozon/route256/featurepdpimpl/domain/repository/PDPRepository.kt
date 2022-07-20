package ru.ozon.route256.featurepdpimpl.domain.repository

import io.reactivex.Single
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO

interface PDPRepository {
    fun getProductsDetailPageDTO(): Single<List<ProductDetailPageDTO>>
    fun changeValue(lastValue: Int, guid: String?)
    fun getCart(): List<InCartModel>?
}
