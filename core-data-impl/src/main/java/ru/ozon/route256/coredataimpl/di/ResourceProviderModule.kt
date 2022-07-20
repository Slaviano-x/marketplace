package ru.ozon.route256.coredataimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.coredataapi.ResourceProvider
import ru.ozon.route256.coredataimpl.resourceprovider.ResourceProviderImpl

@Module
interface ResourceProviderModule {
    @Binds
    fun bindResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider
}
