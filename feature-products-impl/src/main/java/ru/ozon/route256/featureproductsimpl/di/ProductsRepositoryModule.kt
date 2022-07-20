package ru.ozon.route256.featureproductsimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.featureproductsimpl.data.repositoryimpl.ProductsRepositoryImpl
import ru.ozon.route256.featureproductsimpl.domain.repository.ProductsRepository

@Module
interface ProductsRepositoryModule {

    @Binds
    fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}
