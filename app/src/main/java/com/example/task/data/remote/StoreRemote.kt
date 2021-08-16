package com.example.task.data.remote

import com.example.task.ui.models.BrandResponse
import com.example.task.ui.models.ProductDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface StoreRemote {
    @GET("brands/259940351")
    suspend fun getBrandDetails(@Query("page") page: Int? = null,
                                @Query("per_page") perPage: Int?= null): BrandResponse

    @GET("products/{id}/details")
    suspend fun getProductDetails(@Path("id") id :String): ProductDetailsResponse


}