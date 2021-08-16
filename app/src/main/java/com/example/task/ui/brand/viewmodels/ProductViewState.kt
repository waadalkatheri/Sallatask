package com.example.task.ui.brand.viewmodels

import androidx.paging.PagingData
import com.example.task.ui.models.Product

// viewState class to describe screen screen actions to make user restricted to them
sealed class ProductViewState {
    object LOADING : ProductViewState()
    class SUCCESS(val payload: PagingData<Product> = PagingData.empty()) : ProductViewState()
    class FAILURE(val errorMsg: String) : ProductViewState()
}