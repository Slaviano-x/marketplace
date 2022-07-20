package ru.ozon.route256.corenetworkimpl.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.ozon.route256.corenetworkapi.ProductServiceApi

@Module
class ProductsServiceApiModule {
    @Provides
    fun provideProductsServiceApi(retrofit: Retrofit): ProductServiceApi =
        retrofit.create(ProductServiceApi::class.java)
}
