package ru.ozon.route256.featureaddproductapi

import android.app.Application
import androidx.fragment.app.Fragment

interface AddProductNavigationApi {
    fun navigateToProductList(fragment: Fragment, application: Application)
    fun isFeatureClosed(fragment: Fragment): Boolean
}
