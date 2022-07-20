package ru.ozon.route256.featurepdpimpl.data.repositoryimpl

import io.reactivex.Single
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.featurepdpimpl.data.SharedPreferencesManager
import ru.ozon.route256.featurepdpimpl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(
    private val productServiceApi: ProductServiceApi,
    private val sharedPreferencesManager: SharedPreferencesManager
) : PDPRepository {
    override fun getProductsDetailPageDTO(): Single<List<ProductDetailPageDTO>> {
        return productServiceApi.getProductsDetailPageDTO()
    }

    override fun changeValue(lastValue: Int, guid: String?) {
        sharedPreferencesManager.changeValue(lastValue, guid)
    }

    override fun getCart(): List<InCartModel>? =
        sharedPreferencesManager.getCart()
}
