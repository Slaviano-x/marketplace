package ru.ozon.route256.corenavigationimpl.di

import android.app.Application
import android.content.Context
import ru.ozon.route256.coredataimpl.di.DaggerCoreDataComponent
import ru.ozon.route256.corenetworkimpl.di.DaggerCoreNetworkComponent
import ru.ozon.route256.featureaddproductimpl.di.DaggerFeatureAddProductComponent_FeatureAddProductDependenciesComponent
import ru.ozon.route256.featureaddproductimpl.di.FeatureAddProductComponent
import ru.ozon.route256.featurepdpimpl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import ru.ozon.route256.featurepdpimpl.di.PDPFeatureComponent
import ru.ozon.route256.featureproductsimpl.di.DaggerFeatureProductsComponent_FeatureProductDependenciesComponent
import ru.ozon.route256.featureproductsimpl.di.FeatureProductsComponent
import ru.ozon.route256.workmanagerimpl.di.DaggerWorkManagerComponent
import ru.ozon.route256.workmanagerimpl.di.DaggerWorkerDependenciesComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI(application: Application) {
        FeatureProductsComponent.initAndGet(
            DaggerFeatureProductsComponent_FeatureProductDependenciesComponent.builder()
                .dataApi(
                    DaggerCoreDataComponent.builder()
                        .appContext(application.applicationContext)
                        .build()
                )
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .workManagerApi(
                    DaggerWorkManagerComponent.builder()
                        .appContext(application.applicationContext)
                        .workManagerDependencies(
                            DaggerWorkerDependenciesComponent.builder()
                                .networkApi(DaggerCoreNetworkComponent.builder().build())
                                .build()
                        )
                        .build()
                )
                .build()
        )
    }

    fun initFeaturePDPDI(context: Context) {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .dataApi(
                    DaggerCoreDataComponent.builder()
                        .appContext(context)
                        .build()
                )
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .pDPNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getPDPNavigation()
                )
                .build()
        )
    }

    fun initFeatureAddProductDI(context: Context) {
        FeatureAddProductComponent.initAndGet(
            DaggerFeatureAddProductComponent_FeatureAddProductDependenciesComponent.builder()
                .dataApi(
                    DaggerCoreDataComponent.builder()
                        .appContext(context)
                        .build()
                )
                .addProductNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getAddProductNavigation()
                )
                .build(),
        )
    }
}
