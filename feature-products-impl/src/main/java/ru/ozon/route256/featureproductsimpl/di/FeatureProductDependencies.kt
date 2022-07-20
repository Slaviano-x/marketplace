package ru.ozon.route256.featureproductsimpl.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.coredataapi.ResourceProvider
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.featureproductsapi.ProductNavigationApi
import ru.ozon.route256.workmanagerapi.WorkManagerApi

interface FeatureProductDependencies {
    fun getProductServiceApi(): ProductServiceApi
    fun getSharedPreferences(): SharedPreferences
    fun getMoshi(): Moshi
    fun getResourceProvider(): ResourceProvider
    fun productNavigationApi(): ProductNavigationApi
    fun workManagerApi(): WorkManagerApi
}
