package ru.ozon.route256.featureaddproductimpl.domain.repository

import ru.ozon.route256.corenetworkapi.model.ProductForAddingDTO

interface AddProductRepository {
    fun addProduct(product: ProductForAddingDTO)
}
