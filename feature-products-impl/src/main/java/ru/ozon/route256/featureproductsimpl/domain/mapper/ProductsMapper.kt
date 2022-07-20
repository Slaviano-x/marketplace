package ru.ozon.route256.featureproductsimpl.domain.mapper

import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.featureproductsimpl.presentation.viewobject.ProductVO

fun ProductDTO.toVO(): ProductVO {
    return ProductVO(
        guid = guid,
        images = image,
        name = name,
        price = price,
        rating = rating.toFloat(),
        isFavorite = isFavorite,
        isInCart = isInCart
    )
}
