package ru.ozon.route256.featurepdpimpl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.featurepdpimpl.data.repositoryimpl.PDPRepositoryImpl
import ru.ozon.route256.featurepdpimpl.domain.repository.PDPRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindPDPRepository(repository: PDPRepositoryImpl): PDPRepository
}
