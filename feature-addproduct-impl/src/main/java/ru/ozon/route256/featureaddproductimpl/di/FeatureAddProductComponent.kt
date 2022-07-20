package ru.ozon.route256.featureaddproductimpl.di

import dagger.Component
import ru.ozon.route256.core.di.PerFeature
import ru.ozon.route256.coredataapi.DataApi
import ru.ozon.route256.featureaddproductapi.AddProductNavigationApi
import ru.ozon.route256.featureaddproductimpl.presentation.view.AddProductFragment

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class
    ],
    dependencies = [FeatureAddProductDependencies::class]
)
@PerFeature
abstract class FeatureAddProductComponent {

    companion object {

        @Volatile
        var featureAddProductComponent: FeatureAddProductComponent? = null
            private set

        fun initAndGet(
            featureAddProductDependencies: FeatureAddProductDependencies
        ): FeatureAddProductComponent? {
            if (featureAddProductComponent == null) {
                synchronized(FeatureAddProductComponent::class) {
                    featureAddProductComponent = DaggerFeatureAddProductComponent.builder()
                        .featureAddProductDependencies(featureAddProductDependencies)
                        .build()
                }
            }
            return featureAddProductComponent
        }

        fun get(): FeatureAddProductComponent? {
            if (featureAddProductComponent == null) {
                throw RuntimeException("You must call 'initAndGet(featureAddProductDependencies: FeatureAddProductDependencies)' method")
            }
            return featureAddProductComponent
        }

        fun resetComponent() {
            featureAddProductComponent = null
        }
    }

    abstract fun inject(fragment: AddProductFragment)

    @PerFeature
    @Component(dependencies = [DataApi::class, AddProductNavigationApi::class])
    interface FeatureAddProductDependenciesComponent : FeatureAddProductDependencies
}
