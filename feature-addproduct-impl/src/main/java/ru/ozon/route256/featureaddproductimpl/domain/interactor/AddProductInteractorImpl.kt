package ru.ozon.route256.featureaddproductimpl.domain.interactor

import ru.ozon.route256.corenetworkapi.model.ProductForAddingDTO
import ru.ozon.route256.featureaddproductimpl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductInteractorImpl @Inject constructor(
    private val repository: AddProductRepository
) : AddProductInteractor {

    override fun addProduct(name: String, price: String, image: String) {
        repository.addProduct(ProductForAddingDTO(image = image, name = name, price = price))
    }
}
