package ru.ozon.route256.coredataapi

import android.content.SharedPreferences
import com.squareup.moshi.Moshi

interface DataApi {
    fun getSharedPreferences(): SharedPreferences
    fun getMoshi(): Moshi
    fun getResourceProvider(): ResourceProvider
}
