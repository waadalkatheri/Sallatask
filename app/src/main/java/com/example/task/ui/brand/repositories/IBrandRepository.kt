package com.example.task.ui.brand.repositories

import androidx.paging.PagingData
import com.example.task.ui.models.Brand
import com.example.task.ui.models.BrandResponse
import com.example.task.ui.models.Product
import kotlinx.coroutines.flow.Flow

interface IBrandRepository {
    fun getBrandDetails(): Flow<PagingData<Product>>
    suspend fun getBrand(): Brand

}