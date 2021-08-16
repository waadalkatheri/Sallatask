package com.example.task.ui.brand.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.task.data.remote.StoreRemote
import com.example.task.ui.brand.pagination.BrandPagingSource
import com.example.task.ui.models.Brand
import com.example.task.ui.models.Product
import kotlinx.coroutines.flow.Flow

class BrandRepository(private val storeRemote: StoreRemote) : IBrandRepository {

    override fun getBrandDetails(): Flow<PagingData<Product>> = Pager(
        config = PagingConfig(
            pageSize = 5,
            prefetchDistance = 5,
            initialLoadSize = 5
        ),
        pagingSourceFactory = { BrandPagingSource(storeRemote) }
    ).flow

    override suspend fun getBrand(): Brand = storeRemote.getBrandDetails().brand


}