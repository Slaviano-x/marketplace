package ru.ozon.route256.coredataimpl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.coredataapi.DataApi
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SharedPreferencesModule::class,
        MoshiModule::class,
        ResourceProviderModule::class
    ]
)
interface CoreDataComponent : DataApi {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(appContext: Context): Builder
        fun build(): CoreDataComponent
    }
}
