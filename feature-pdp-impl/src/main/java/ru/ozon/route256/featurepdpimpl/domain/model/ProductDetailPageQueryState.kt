package ru.ozon.route256.featurepdpimpl.domain.model

import ru.ozon.route256.featurepdpimpl.presentation.viewobject.ProductVO

sealed class ProductDetailPageQueryState {
    data class Success(val product: ProductVO) : ProductDetailPageQueryState()
    object Error : ProductDetailPageQueryState()
    object Loading : ProductDetailPageQueryState()
}
