package ru.ozon.route256.corenavigationimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.corenavigationimpl.navigation.AddProductNavigationApiImpl
import ru.ozon.route256.corenavigationimpl.navigation.PDPNavigationImpl
import ru.ozon.route256.corenavigationimpl.navigation.ProductNavigationImpl
import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi
import ru.ozon.route256.featurepdpapi.PDPNavigationApi
import ru.ozon.route256.featureproductsapi.ProductNavigationApi

@Module
interface NavigationModule {

    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi

    @Binds
    fun bindPDPNavigation(navigation: PDPNavigationImpl): PDPNavigationApi

    @Binds
    fun bindAddProductNavigation(navigation: AddProductNavigationApiImpl): AddProductNavigationApi
}
