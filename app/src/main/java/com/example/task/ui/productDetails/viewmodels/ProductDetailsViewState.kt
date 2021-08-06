package com.example.task.ui.productDetails.viewmodels

import com.example.task.ui.models.ProductDetailsResponse

sealed class ProductDetailsViewState {
    object LOADING : ProductDetailsViewState()
    class SUCCESS(val payload: ProductDetailsResponse) : ProductDetailsViewState()
    class FAILURE(val errorMsg: String) : ProductDetailsViewState()
}