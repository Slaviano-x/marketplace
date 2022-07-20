package ru.ozon.route256.featurepdpimpl.domain.mapper

import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.featurepdpimpl.presentation.viewobject.ProductVO

fun ProductDetailPageDTO.toVO(): ProductVO =
    ProductVO(
        guid,
        name,
        price,
        description,
        rating,
        isFavorite,
        isInCart,
        images,
        weight,
        count ?: 0,
        availableCount,
        additionalParams
    )
