package com.example.task.ui.productDetails.repositories

import com.example.task.data.remote.StoreRemote
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
class ProductRepository : IProductRepository, KoinComponent {
    // inject api interface using koin
    private val storeRemote: StoreRemote by inject()
    override suspend fun getProductDetails(id:String) = storeRemote.getProductDetails(id)
}