package ru.ozon.route256.featureaddproductimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.featureaddproductimpl.domain.interactor.AddProductInteractor
import ru.ozon.route256.featureaddproductimpl.domain.interactor.AddProductInteractorImpl

@Module
interface InteractorModule {
    @Binds
    fun bindAddProductInteractor(interactor: AddProductInteractorImpl): AddProductInteractor
}
