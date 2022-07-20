package ru.ozon.route256.featurepdpimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.featurepdpimpl.domain.interactor.PDPInteractor
import ru.ozon.route256.featurepdpimpl.domain.interactor.PDPInteractorImpl

@Module
interface InteractorModule {
    @Binds
    fun bindPDPInteractor(interactor: PDPInteractorImpl): PDPInteractor
}
