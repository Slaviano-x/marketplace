package ru.ozon.route256.marketplace.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.marketplace.MainApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ): AppComponent
    }

    fun inject(application: MainApplication)
}
