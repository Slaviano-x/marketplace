package ru.ozon.route256.featureproductsimpl.di

import dagger.Module
import dagger.Provides
import ru.ozon.route256.core.di.PerFeature
import ru.ozon.route256.featureproductsimpl.domain.interactor.ProductsInteractor
import ru.ozon.route256.featureproductsimpl.domain.interactor.ProductsInteractorImpl
import ru.ozon.route256.featureproductsimpl.domain.repository.ProductsRepository

@Module
class InteractorModule {
    @Provides
    @PerFeature
    fun provideProductsInteractor(repository: ProductsRepository): ProductsInteractor =
        ProductsInteractorImpl(repository)
}
