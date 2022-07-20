package ru.ozon.route256.coredataimpl.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.ozon.route256.util.const.PreferencesConst
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            PreferencesConst.MAIN_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
}
