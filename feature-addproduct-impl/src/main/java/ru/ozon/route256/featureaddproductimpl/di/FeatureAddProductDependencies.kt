package ru.ozon.route256.featureaddproductimpl.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi

interface FeatureAddProductDependencies {
    fun addProductNavigationApi(): AddProductNavigationApi
    fun getSharedPreferences(): SharedPreferences
    fun getMoshi(): Moshi
}
