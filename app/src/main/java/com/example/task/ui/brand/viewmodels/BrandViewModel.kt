package com.example.task.ui.brand.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.task.ui.brand.repositories.IBrandRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BrandViewModel(private val repository: IBrandRepository) : ViewModel() {
    private val _products = MutableLiveData<ProductViewState>()
    val products: LiveData<ProductViewState> = _products

    private val _brand = MutableLiveData<BrandViewState>()
    val brand: LiveData<BrandViewState> = _brand

    fun getProducts() = viewModelScope.launch {
        try {
            repository.getBrandDetails().onEach {
                _products.value = ProductViewState.LOADING

            }
                .cachedIn(viewModelScope).collectLatest {
                    _products.value = ProductViewState.SUCCESS(payload = it)
                }
        } catch (e: Exception) {
            _products.value = ProductViewState.FAILURE(errorMsg = e.message!!)

        }

    }

    fun getBrand() = viewModelScope.launch {
        try {
            _brand.value = BrandViewState.LOADING
            val brand = repository.getBrand()
            _brand.value = BrandViewState.SUCCESS(payload = brand)
        } catch (e: Exception) {
            _brand.value = BrandViewState.FAILURE(errorMsg = e.message!!)

        }


    }
}