package ru.ozon.route256.workmanagerimpl

import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import ru.ozon.route256.workmanagerapi.AsyncProductsLoader
import ru.ozon.route256.workmanagerapi.WorkerKey.COMMON_WORKER_TAG
import ru.ozon.route256.workmanagerapi.WorkerKey.PRODUCTS_DETAIL_PAGE_DTO_TAG
import ru.ozon.route256.workmanagerapi.WorkerKey.PRODUCTS_DTO_TAG
import javax.inject.Inject

class ProductsWorkManager @Inject constructor(
    private val workManager: WorkManager,
) : AsyncProductsLoader {

    override fun loadProductsAsync(): LiveData<WorkInfo> {
        workManager.pruneWork()

        val getProductsDTO = OneTimeWorkRequest.Builder(ProductsDTOWorker::class.java)
            .addTag(COMMON_WORKER_TAG)
            .addTag(PRODUCTS_DTO_TAG)
            .build()

        val getProductsDetailPageDTO =
            OneTimeWorkRequest.Builder(ProductDetailPageDTOWorker::class.java)
                .addTag(COMMON_WORKER_TAG)
                .addTag(PRODUCTS_DETAIL_PAGE_DTO_TAG)
                .build()

        workManager
            .beginWith(getProductsDTO)
            .then(getProductsDetailPageDTO)
            .enqueue()

        return workManager.getWorkInfoByIdLiveData(getProductsDetailPageDTO.id)
    }
}
