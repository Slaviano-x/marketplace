package ru.ozon.route256.featureaddproductimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.featureaddproductimpl.data.repositoryimpl.AddProductRepositoryImpl
import ru.ozon.route256.featureaddproductimpl.domain.repository.AddProductRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindAddProductRepository(repository: AddProductRepositoryImpl): AddProductRepository
}
