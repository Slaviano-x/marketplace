package ru.ozon.route256.featureproductsimpl.domain.model

import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

sealed class ProductsQueryState {
    data class Success(val productList: List<ViewHolderModel>) : ProductsQueryState()
    object Error : ProductsQueryState()
    object Loading : ProductsQueryState()
}
