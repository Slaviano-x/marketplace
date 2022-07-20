package ru.ozon.route256.corenetworkapi

import io.reactivex.Single
import retrofit2.http.GET
import ru.ozon.route256.corenetworkapi.model.ProductDTO
import ru.ozon.route256.corenetworkapi.model.ProductDetailPageDTO

interface ProductServiceApi {
    @GET("ee6876a1-8c02-45aa-bde4-b91817a8b210/")
    fun getProductsDTO(): Single<List<ProductDTO>>
    @GET("d1b4763b-a5ea-471f-83bf-796da466e3d8/")
    fun getProductsDetailPageDTO(): Single<List<ProductDetailPageDTO>>
}
