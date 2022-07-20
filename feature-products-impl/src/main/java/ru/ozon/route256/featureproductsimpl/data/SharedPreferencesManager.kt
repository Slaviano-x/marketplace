package ru.ozon.route256.featureproductsimpl.data

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import ru.ozon.route256.corenetworkapi.extension.adapterInCartList
import ru.ozon.route256.corenetworkapi.extension.adapterProductsDTO
import ru.ozon.route256.corenetworkapi.extension.adapterProductsDetailPageDTO
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.featureproductsimpl.domain.model.IsInCartState
import ru.ozon.route256.util.const.PreferencesConst
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi
) {
    private val editor = sharedPreferences.edit()

    fun getProductsDTOList(): List<ProductDTO>? {
        val cachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_PRODUCTS_DTO,
            null
        )
        return cachedData?.let { moshi.adapterProductsDTO().fromJson(it) }
    }

    fun refreshCachedData(
        productsDTOResponse: String,
        productsDetailPageDTOResponse: String
    ): List<ProductDTO> {
        val actualProductsDTOList: List<ProductDTO>?

        val addingProductsDTOCachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DTO,
            null
        )

        val addingProductsDetailPageDTOCachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_ADDING_PRODUCTS_DETAIL_PAGE_DTO,
            null
        )

        val newProductsDTOJsonList =
            if (addingProductsDTOCachedData != null) {
                val addingList = moshi.adapterProductsDTO().fromJson(addingProductsDTOCachedData)
                val actualList =
                    moshi.adapterProductsDTO().fromJson(productsDTOResponse)?.toMutableList()

                if (addingList != null && actualList != null) {
                    actualList.addAll(addingList)
                }
                actualProductsDTOList = actualList
                moshi.adapterProductsDTO().toJson(actualList)
            } else {
                actualProductsDTOList = moshi.adapterProductsDTO().fromJson(productsDTOResponse)
                productsDTOResponse
            }

        val newProductsDetailPageDTOJsonList =
            if (addingProductsDetailPageDTOCachedData != null) {
                val addingList =
                    moshi.adapterProductsDetailPageDTO()
                        .fromJson(addingProductsDetailPageDTOCachedData)
                val actualList =
                    moshi.adapterProductsDetailPageDTO().fromJson(productsDetailPageDTOResponse)
                        ?.toMutableList()

                if (addingList != null && actualList != null) {
                    actualList.toMutableList().addAll(addingList)
                }
                moshi.adapterProductsDetailPageDTO().toJson(actualList)
            } else {
                productsDTOResponse
            }

        saveLists(newProductsDTOJsonList, newProductsDetailPageDTOJsonList)

        return actualProductsDTOList ?: emptyList()
    }

    fun addOrRemoveFromCart(guid: String): Observable<IsInCartState> {
        var inCartList: MutableList<InCartModel>? = getInCartList()?.toMutableList()
        val inCartCount = inCartList?.find { it.guid == guid }?.count

        if (inCartList == null) {
            inCartList = mutableListOf()
        }
        if (inCartCount == null) {
            inCartList.add(InCartModel(guid, 0))
        }
        val isInCart = if (inCartCount != null && inCartCount > 0) {
            inCartList.find { it.guid == guid }?.count = 0
            false
        } else {
            inCartList.find { it.guid == guid }?.count = 1
            true
        }

        saveLists(inCartList = moshi.adapterInCartList().toJson(inCartList))
        return Observable.just(IsInCartState.Success(isInCart))
    }

    fun getCart(): List<InCartModel>? = getInCartList()

    private fun saveLists(
        productsDTOList: String? = null,
        productsDetailPageDTOList: String? = null,
        inCartList: String? = null
    ) {
        if (productsDTOList != "") {
            editor.putString(PreferencesConst.KEY_CACHED_PRODUCTS_DTO, productsDTOList)
        }
        if (productsDetailPageDTOList != "") {
            editor.putString(
                PreferencesConst.KEY_CACHED_PRODUCTS_DETAIL_PAGE_DTO,
                productsDetailPageDTOList
            )
        }
        if (inCartList != "") {
            editor.putString(PreferencesConst.KEY_CACHED_IN_CART_LIST, inCartList)
        }
        editor.apply()
    }

    private fun getInCartList(): List<InCartModel>? {
        val cachedData = sharedPreferences.getString(
            PreferencesConst.KEY_CACHED_IN_CART_LIST,
            null
        )
        return cachedData?.let { moshi.adapterInCartList().fromJson(it) }
    }
}
