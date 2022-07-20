package ru.ozon.route256.corenetworkapi.model

data class ProductDTO(
    val guid: String,
    val image: List<String>,
    val isFavorite: Boolean,
    var isInCart: Boolean,
    val name: String,
    val price: String,
    val rating: Double
)
