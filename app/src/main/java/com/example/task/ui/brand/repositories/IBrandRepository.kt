package com.example.task.ui.brand.repositories

import com.example.task.ui.models.BrandResponse

interface IBrandRepository {
    suspend fun getBrandDetails(): BrandResponse
}