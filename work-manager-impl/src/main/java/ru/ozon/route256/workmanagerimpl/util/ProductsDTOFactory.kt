package ru.ozon.route256.workmanagerimpl.util

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.workmanagerimpl.ProductsDTOWorker

class ProductsDTOFactory(
    private val productServiceApi: ProductServiceApi,
    private val moshi: Moshi,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            ProductsDTOWorker::class.java.name ->
                ProductsDTOWorker(
                    appContext,
                    workerParameters,
                    productServiceApi,
                    moshi
                )
            else -> null
        }
    }
}
