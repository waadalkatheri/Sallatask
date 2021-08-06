package com.example.task.ui.productDetails.repositories

import com.example.task.ui.models.ProductDetailsResponse

interface IProductRepository {
    suspend fun getProductDetails(id:String): ProductDetailsResponse
}