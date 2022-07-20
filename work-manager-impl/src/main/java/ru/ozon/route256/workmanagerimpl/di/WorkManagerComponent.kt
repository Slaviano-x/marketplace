package ru.ozon.route256.workmanagerimpl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.corenetworkapi.NetworkApi
import ru.ozon.route256.workmanagerapi.WorkManagerApi
import javax.inject.Singleton

interface WorkManagerDependencies {
    fun networkApi(): NetworkApi
}

@Singleton
@Component(dependencies = [NetworkApi::class])
interface WorkerDependenciesComponent : WorkManagerDependencies

@Component(
    modules = [WorkerModule::class],
    dependencies = [WorkManagerDependencies::class]
)
abstract class WorkManagerComponent : WorkManagerApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(appContext: Context): Builder
        fun workManagerDependencies(workManagerDependencies: WorkManagerDependencies): Builder
        fun build(): WorkManagerComponent
    }
}
