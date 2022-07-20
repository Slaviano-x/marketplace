package ru.ozon.route256.corenetworkapi.extension

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.ozon.route256.corenetworkapi.model.InCartModel
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO

fun Moshi.adapterProductsDTO(): JsonAdapter<List<ProductDTO>> =
    this.adapter(Types.newParameterizedType(List::class.java, ProductDTO::class.java))

fun Moshi.adapterProductsDetailPageDTO(): JsonAdapter<List<ProductDetailPageDTO>> =
    this.adapter(Types.newParameterizedType(List::class.java, ProductDetailPageDTO::class.java))

fun Moshi.adapterInCartList(): JsonAdapter<List<InCartModel>> =
    this.adapter(Types.newParameterizedType(List::class.java, InCartModel::class.java))
