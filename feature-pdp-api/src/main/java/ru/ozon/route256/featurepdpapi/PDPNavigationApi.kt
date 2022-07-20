package ru.ozon.route256.featurepdpapi

import androidx.fragment.app.Fragment

interface PDPNavigationApi {
    fun isFeatureClosed(fragment: Fragment): Boolean
}
