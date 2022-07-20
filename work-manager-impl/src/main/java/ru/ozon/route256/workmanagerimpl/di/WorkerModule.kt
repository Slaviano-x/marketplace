package ru.ozon.route256.workmanagerimpl.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.ozon.route256.workmanagerapi.AsyncProductsLoader
import ru.ozon.route256.workmanagerimpl.ProductsWorkManager

@Module
abstract class WorkerModule {
    companion object {
        @Provides
        fun provideWorkManager(context: Context): WorkManager {
            return WorkManager.getInstance(context)
        }
    }

    @Binds
    abstract fun bindsAsyncProductsLoader(impl: ProductsWorkManager): AsyncProductsLoader
}
