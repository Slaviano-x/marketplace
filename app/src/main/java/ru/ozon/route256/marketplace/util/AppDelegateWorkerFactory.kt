package ru.ozon.route256.marketplace.util

import androidx.work.DelegatingWorkerFactory
import com.squareup.moshi.Moshi
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.workmanagerimpl.util.ProductsDTOFactory
import ru.ozon.route256.workmanagerimpl.util.ProductsDetailPageDTOFactory
import javax.inject.Inject

class AppDelegateWorkerFactory @Inject constructor(
    productServiceApi: ProductServiceApi,
    moshi: Moshi
) : DelegatingWorkerFactory() {

    init {
        addFactory(
            ProductsDTOFactory(
                productServiceApi,
                moshi
            )
        )
        addFactory(
            ProductsDetailPageDTOFactory(
                productServiceApi,
                moshi
            )
        )
    }
}
