package ru.ozon.route256.featureaddproductimpl.data.repositoryimpl

import ru.ozon.route256.corenetworkapi.model.ProductForAddingDTO
import ru.ozon.route256.featureaddproductimpl.data.SharedPreferencesManager
import ru.ozon.route256.featureaddproductimpl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    private val manager: SharedPreferencesManager
) : AddProductRepository {
    override fun addProduct(product: ProductForAddingDTO) {
        manager.addProduct(product)
    }
}
