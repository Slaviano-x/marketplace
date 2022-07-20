package ru.ozon.route256.corenavigationimpl.navigation

import android.app.Application
import androidx.fragment.app.Fragment
import ru.ozon.route256.corenavigationimpl.R
import ru.ozon.route256.corenavigationimpl.di.FeatureInjectorProxy
import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi
import ru.ozon.route256.featureaddproductimpl.presentation.view.AddProductFragment
import ru.ozon.route256.featureproductsimpl.presentation.view.ProductsFragment
import javax.inject.Inject

class AddProductNavigationApiImpl @Inject constructor() : AddProductNavigationApi {
    override fun navigateToProductList(fragment: Fragment, application: Application) {
        FeatureInjectorProxy.initFeatureProductsDI(application)
        val newFragment = ProductsFragment()
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, ProductsFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != AddProductFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(
                AddProductFragment::class.java.simpleName
            ) == null
        } else {
            true
        }
    }
}
