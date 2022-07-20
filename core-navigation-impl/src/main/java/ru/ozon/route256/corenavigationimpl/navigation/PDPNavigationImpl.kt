package ru.ozon.route256.corenavigationimpl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.featurepdpapi.PDPNavigationApi
import ru.ozon.route256.featurepdpimpl.presentation.view.PDPFragment
import javax.inject.Inject

class PDPNavigationImpl @Inject constructor() : PDPNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != PDPFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(
                PDPFragment::class.java.simpleName
            ) == null
        } else {
            true
        }
    }
}
