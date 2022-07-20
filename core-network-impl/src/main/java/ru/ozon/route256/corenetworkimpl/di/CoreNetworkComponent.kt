package ru.ozon.route256.corenetworkimpl.di

import dagger.Component
import ru.ozon.route256.corenetworkapi.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        ProductsServiceApiModule::class
    ]
)
interface CoreNetworkComponent : NetworkApi
