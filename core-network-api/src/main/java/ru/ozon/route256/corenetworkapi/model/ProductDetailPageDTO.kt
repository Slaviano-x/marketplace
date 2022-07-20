package ru.ozon.route256.corenetworkapi.model

data class ProductDetailPageDTO(
    val guid: String,
    val name: String,
    val price: String,
    val description: String,
    val rating: Double,
    val isFavorite: Boolean,
    var isInCart: Boolean,
    val images: List<String>,
    val weight: Double?,
    var count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>
)
