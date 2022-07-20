package ru.ozon.route256.workmanagerapi

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface AsyncProductsLoader {
    fun loadProductsAsync(): LiveData<WorkInfo>
}
