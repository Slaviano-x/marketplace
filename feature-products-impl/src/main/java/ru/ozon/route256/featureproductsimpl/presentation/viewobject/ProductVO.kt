package ru.ozon.route256.featureproductsimpl.presentation.viewobject

import ru.ozon.route256.util.presentation.viewholder.ViewHolderModel

data class ProductVO(
    val guid: String,
    val images: List<String>,
    val name: String,
    val price: String,
    val rating: Float,
    var isFavorite: Boolean,
    var isInCart: Boolean
) : ViewHolderModel
