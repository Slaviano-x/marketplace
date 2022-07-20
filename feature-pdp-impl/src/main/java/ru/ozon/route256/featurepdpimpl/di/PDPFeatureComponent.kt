package ru.ozon.route256.featurepdpimpl.di

import dagger.Component
import ru.ozon.route256.core.di.PerFeature
import ru.ozon.route256.coredataapi.DataApi
import ru.ozon.route256.corenetworkapi.NetworkApi
import ru.ozon.route256.featurepdpapi.PDPNavigationApi
import ru.ozon.route256.featurepdpimpl.presentation.view.PDPFragment

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
    ],
    dependencies = [PDPFeatureDependencies::class]
)
@PerFeature
abstract class PDPFeatureComponent {

    companion object {

        @Volatile
        var pdpFeatureComponent: PDPFeatureComponent? = null
            private set

        fun initAndGet(
            pdpFeatureDependencies: PDPFeatureDependencies,
        ): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                synchronized(PDPFeatureComponent::class) {
                    pdpFeatureComponent = DaggerPDPFeatureComponent.builder()
                        .pDPFeatureDependencies(pdpFeatureDependencies)
                        .build()
                }
            }
            return pdpFeatureComponent
        }

        fun get(): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return pdpFeatureComponent
        }

        fun resetComponent() {
            pdpFeatureComponent = null
        }
    }

    abstract fun inject(fragment: PDPFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, DataApi::class, PDPNavigationApi::class])
    interface PDPFeatureDependenciesComponent : PDPFeatureDependencies
}
