package ru.ozon.route256.featureproductsimpl.domain.model

sealed class IsInCartState {
    data class Success(val isInCart: Boolean) : IsInCartState()
    object Error : IsInCartState()
}
