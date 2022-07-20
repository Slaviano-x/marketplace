package ru.ozon.route256.marketplace

import android.app.Application
import androidx.work.Configuration
import ru.ozon.route256.marketplace.di.DaggerAppComponent
import ru.ozon.route256.marketplace.util.AppDelegateWorkerFactory
import javax.inject.Inject

class MainApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var appDelegateWorkerFactory: AppDelegateWorkerFactory

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(this, this).inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(appDelegateWorkerFactory)
            .build()
}
