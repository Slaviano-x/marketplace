package ru.ozon.route256.featureproductsapi

import androidx.fragment.app.Fragment

interface ProductNavigationApi {
    fun navigateToPDP(fragment: Fragment, guid: String)
    fun navigateToAddProduct(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}
