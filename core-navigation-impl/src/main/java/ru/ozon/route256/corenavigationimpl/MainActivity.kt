package ru.ozon.route256.corenavigationimpl

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import ru.ozon.route256.core.presentation.BaseFragment
import ru.ozon.route256.corenavigationimpl.databinding.ActivityMainBinding
import ru.ozon.route256.corenavigationimpl.di.FeatureInjectorProxy
import ru.ozon.route256.featureproductsimpl.presentation.view.ProductsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? BaseFragment

    private val networkCallback = getNetworkCallBack()

    override fun onResume() {
        super.onResume()
        getConnectivityManager().registerDefaultNetworkCallback(networkCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateProduct()
    }

    override fun onPause() {
        super.onPause()
        getConnectivityManager().unregisterNetworkCallback(networkCallback)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun navigateProduct() {
        FeatureInjectorProxy.initFeatureProductsDI(application)
        val newFragment = ProductsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, ProductsFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun getConnectivityManager() =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private fun getNetworkCallBack(): ConnectivityManager.NetworkCallback {
        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                showLostInternetNotification(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                showLostInternetNotification(true)
            }
        }
    }

    private fun showLostInternetNotification(isShow: Boolean) {
        runOnUiThread {
            binding.lostInternetNotification.isVisible = isShow
        }
    }
}
