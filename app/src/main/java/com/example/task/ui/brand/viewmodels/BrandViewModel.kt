package com.example.task.ui.brand.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.ui.brand.repositories.IBrandRepository
import kotlinx.coroutines.launch

class BrandViewModel(private val repository: IBrandRepository) : ViewModel() {
    private val _response = MutableLiveData<BrandViewState>()
    val response: LiveData<BrandViewState> = _response

    init {
        viewModelScope.launch {
            try {
                _response.value = BrandViewState.LOADING
                val result = repository.getBrandDetails()
                _response.value = BrandViewState.SUCCESS(payload = result)
            } catch (e: Exception) {
                _response.value = BrandViewState.FAILURE(errorMsg = e.message!!)
            }

        }
    }
}