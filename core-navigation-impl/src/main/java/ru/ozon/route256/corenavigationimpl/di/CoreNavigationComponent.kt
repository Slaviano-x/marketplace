package ru.ozon.route256.corenavigationimpl.di

import dagger.Component
import ru.ozon.route256.corenavigationapi.NavigationApi
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface CoreNavigationComponent : NavigationApi
