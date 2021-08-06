package com.example.task.ui.productDetails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.ui.productDetails.repositories.IProductRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ProductViewModel : ViewModel(), KoinComponent {
    private val repository: IProductRepository by inject()
    private val _productDetails = MutableLiveData<ProductDetailsViewState>()
    val productDetailsDetails: LiveData<ProductDetailsViewState> = _productDetails

    fun getProductDetails(id: String) =
        viewModelScope.launch {
            try {
                _productDetails.value = ProductDetailsViewState.LOADING
                val result = repository.getProductDetails(id)
                _productDetails.value = ProductDetailsViewState.SUCCESS(payload = result)
            } catch (e: Exception) {
                _productDetails.value = ProductDetailsViewState.FAILURE(errorMsg = e.message!!)
            }

        }

}