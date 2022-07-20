package ru.ozon.route256.featureaddproductimpl.data.mapper

import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.corenetworkapi.model.ProductForAddingDTO
import java.util.UUID

private const val DEFAULT_IMAGE =
    "https://www.levsha.spb.ru/content/images/thumbs/default-image_450.png"

fun ProductForAddingDTO.toProductDTO(guid: UUID): ProductDTO {
    val image = this.image.ifEmpty { DEFAULT_IMAGE }
    return ProductDTO(
        guid = guid.toString(),
        image = listOf(image),
        name = this.name,
        price = this.price,
        rating = 0.0,
        isFavorite = false,
        isInCart = false
    )
}

fun ProductForAddingDTO.toProductDetailPageDTO(guid: UUID): ProductDetailPageDTO {
    val image = this.image.ifEmpty { DEFAULT_IMAGE }
    return ProductDetailPageDTO(
        guid = guid.toString(),
        name = this.name,
        price = this.price,
        description = "",
        rating = 0.0,
        isFavorite = false,
        isInCart = false,
        images = listOf(image),
        weight = null,
        count = null,
        availableCount = null,
        additionalParams = emptyMap()
    )
}
