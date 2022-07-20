package ru.ozon.route256.corenavigationimpl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.corenavigationimpl.R
import ru.ozon.route256.corenavigationimpl.di.FeatureInjectorProxy
import ru.ozon.route256.featureaddproductimpl.presentation.view.AddProductFragment
import ru.ozon.route256.featurepdpimpl.presentation.view.PDPFragment
import ru.ozon.route256.featureproductsapi.ProductNavigationApi
import ru.ozon.route256.featureproductsimpl.presentation.view.ProductsFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {
    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI(fragment.requireContext())
        val newFragment = PDPFragment.newInstance(guid)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateToAddProduct(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureAddProductDI(fragment.requireContext())
        val newFragment = AddProductFragment.newInstance()
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragmentContainer,
                newFragment,
                AddProductFragment::class.java.simpleName
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != ProductsFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(
                ProductsFragment::class.java.simpleName
            ) == null
        } else {
            true
        }
    }
}
