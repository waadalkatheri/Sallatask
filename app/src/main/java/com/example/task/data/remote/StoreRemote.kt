package com.example.task.data.remote

import com.example.task.ui.models.BrandResponse
import com.example.task.ui.models.ProductDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreRemote {
    @GET("brands/259940351")
    suspend fun getBrandDetails(): BrandResponse

    @GET("products/{id}/details")
    suspend fun getProductDetails(@Path("id") id :String): ProductDetailsResponse
}