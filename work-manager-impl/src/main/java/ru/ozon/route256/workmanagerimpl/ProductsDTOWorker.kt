package ru.ozon.route256.workmanagerimpl

import android.content.Context
import androidx.work.Data
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Single
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.workmanagerapi.WorkerKey.KEY_RESPONSE_PRODUCTS_DTO
import javax.inject.Inject

class ProductsDTOWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters,
    private val productServiceApi: ProductServiceApi,
    private val moshi: Moshi,
) : RxWorker(context, workerParameters) {

    override fun createWork(): Single<Result> {
        return productServiceApi.getProductsDTO()
            .map { response ->
                val type = Types.newParameterizedType(List::class.java, ProductDTO::class.java)
                val adapter = moshi.adapter<List<ProductDTO>>(type)

                val outputData = Data.Builder()
                    .putString(KEY_RESPONSE_PRODUCTS_DTO, adapter.toJson(response))
                    .build()

                Result.success(outputData)
            }
            .onErrorReturn {
                Result.failure()
            }
    }
}
