package com.example.task.ui.brand.repositories

import com.example.task.data.remote.StoreRemote

class BrandRepository(private val storeRemote: StoreRemote): IBrandRepository {
    override suspend fun getBrandDetails() = storeRemote.getBrandDetails()
}