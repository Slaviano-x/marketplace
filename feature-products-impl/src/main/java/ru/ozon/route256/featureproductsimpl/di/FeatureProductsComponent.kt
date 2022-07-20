package ru.ozon.route256.featureproductsimpl.di

import dagger.Component
import ru.ozon.route256.core.di.PerFeature
import ru.ozon.route256.coredataapi.DataApi
import ru.ozon.route256.corenetworkapi.NetworkApi
import ru.ozon.route256.featureproductsapi.ProductNavigationApi
import ru.ozon.route256.featureproductsimpl.presentation.view.ProductsFragment
import ru.ozon.route256.workmanagerapi.WorkManagerApi

@Component(
    modules = [
        InteractorModule::class,
        ProductsRepositoryModule::class
    ],
    dependencies = [FeatureProductDependencies::class]
)
@PerFeature
abstract class FeatureProductsComponent {

    companion object {

        @Volatile
        var featureProductsComponent: FeatureProductsComponent? = null
            private set

        fun initAndGet(
            featureProductDependencies: FeatureProductDependencies
        ): FeatureProductsComponent? {
            if (featureProductsComponent == null) {
                synchronized(FeatureProductsComponent::class) {
                    featureProductsComponent = DaggerFeatureProductsComponent.builder()
                        .featureProductDependencies(featureProductDependencies)
                        .build()
                }
            }
            return featureProductsComponent
        }

        fun get(): FeatureProductsComponent? {
            if (featureProductsComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return featureProductsComponent
        }

        fun resetComponent() {
            featureProductsComponent = null
        }
    }

    abstract fun inject(fragment: ProductsFragment)

    @PerFeature
    @Component(
        dependencies = [
            NetworkApi::class,
            DataApi::class,
            ProductNavigationApi::class,
            WorkManagerApi::class
        ]
    )
    interface FeatureProductDependenciesComponent : FeatureProductDependencies
}
