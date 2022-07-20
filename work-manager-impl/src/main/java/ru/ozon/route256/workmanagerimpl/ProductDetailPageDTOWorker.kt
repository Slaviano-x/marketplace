package ru.ozon.route256.workmanagerimpl

import android.content.Context
import androidx.work.Data
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Single
import ru.ozon.route256.corenetworkapi.ProductServiceApi
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.workmanagerapi.WorkerKey.KEY_RESPONSE_PRODUCTS_DETAIL_PAGE_DTO
import ru.ozon.route256.workmanagerapi.WorkerKey.KEY_RESPONSE_PRODUCTS_DTO
import javax.inject.Inject

class ProductDetailPageDTOWorker @Inject constructor(
    context: Context,
    workerParameters: WorkerParameters,
    private val productServiceApi: ProductServiceApi,
    private val moshi: Moshi,
) : RxWorker(context, workerParameters) {

    override fun createWork(): Single<Result> {
        return productServiceApi.getProductsDetailPageDTO()
            .map { response ->
                val type =
                    Types.newParameterizedType(List::class.java, ProductDetailPageDTO::class.java)
                val adapter = moshi.adapter<List<ProductDetailPageDTO>>(type)

                val lastWorkerInputData = inputData.getString(KEY_RESPONSE_PRODUCTS_DTO) ?: ""

                val outputData = Data.Builder()
                    .putString(KEY_RESPONSE_PRODUCTS_DETAIL_PAGE_DTO, adapter.toJson(response))
                    .putString(KEY_RESPONSE_PRODUCTS_DTO, lastWorkerInputData)
                    .build()

                Result.success(outputData)
            }
            .onErrorReturn {
                Result.failure()
            }
    }
}
