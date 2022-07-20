package ru.ozon.route256.marketplace.di

import dagger.Module
import ru.ozon.route256.coredataimpl.di.SharedPreferencesModule
import ru.ozon.route256.corenetworkimpl.di.ProductsServiceApiModule
import ru.ozon.route256.corenetworkimpl.di.RetrofitModule

@Module(
    includes = [
        RetrofitModule::class,
        ProductsServiceApiModule::class,
        SharedPreferencesModule::class
    ]
)
class AppModule
