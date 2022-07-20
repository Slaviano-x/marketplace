package ru.ozon.route256.corenavigationapi

import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi
import ru.ozon.route256.featurepdpapi.PDPNavigationApi
import ru.ozon.route256.featureproductsapi.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getPDPNavigation(): PDPNavigationApi
    fun getAddProductNavigation(): AddProductNavigationApi
}
