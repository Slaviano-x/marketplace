package ru.ozon.route256.featurepdpimpl.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.featurepdpapi.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): ProductServiceApi
    fun sharedPreferences(): SharedPreferences
    fun moshi(): Moshi
    fun pdpNavigation(): PDPNavigationApi
}
