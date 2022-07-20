package ru.ozon.route256.featureaddproductimpl.data

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.corenetworkapi.extension.adapterProductsDTO
import ru.ozon.route256.corenetworkapi.extension.adapterProductsDetailPageDTO
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO
import ru.ozon.route256.corenetworkapi.model.ProductForAddingDTO
import ru.ozon.route256.featureaddproductimpl.data.mapper.toProductDTO
import ru.ozon.route256.featureaddproductimpl.data.mapper.toProductDetailPageDTO
import ru.ozon.route256.util.const.PreferencesConst
import java.util.UUID
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) {
    private val editor = sharedPreferences.edit()

    fun addProduct(product: ProductForAddingDTO) {
        val guid = UUID.randomUUID()
        val productDTO = product.toProductDTO(guid)
        val productDetailPageDTO = product.toProductDetailPageDTO(guid)

        refreshAddingCachedProductDTOList(productDTO)
        refreshAddingCachedProductDetailPageDTOList(productDetailPageDTO)
    }

    private fun refreshAddingCachedProductDTOList(productDTO: ProductDTO) {
        val addingCachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DTO,
            null
        )

        val newJsonList = if (addingCachedData != null) {
            val oldList = moshi.adapterProductsDTO().fromJson(addingCachedData)?.toMutableList()
            oldList?.add(productDTO)
            moshi.adapterProductsDTO().toJson(oldList)
        } else {
            moshi.adapterProductsDTO().toJson(listOf(productDTO))
        }

        editor.putString(PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DTO, newJsonList)
        editor.apply()
    }

    private fun refreshAddingCachedProductDetailPageDTOList(
        productDetailPageDTO: ProductDetailPageDTO
    ) {
        val addingCachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DETAIL_PAGE_DTO,
            null
        )

        val newJsonList = if (addingCachedData != null) {
            val oldJsonList =
                moshi.adapterProductsDetailPageDTO().fromJson(addingCachedData)?.toMutableList()
            oldJsonList?.add(productDetailPageDTO)
            moshi.adapterProductsDetailPageDTO().toJson(oldJsonList)
        } else {
            moshi.adapterProductsDetailPageDTO().toJson(listOf(productDetailPageDTO))
        }

        editor.putString(PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DETAIL_PAGE_DTO, newJsonList)
        editor.apply()
    }
}
