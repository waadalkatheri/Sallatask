package com.example.task.ui.brand.viewmodels

import com.example.task.ui.models.Brand

// viewState class to describe screen screen actions to make user restricted to them
sealed class BrandViewState {
    object LOADING : BrandViewState()
    class SUCCESS(val payload: Brand) : BrandViewState()
    class FAILURE(val errorMsg: String) : BrandViewState()
}
