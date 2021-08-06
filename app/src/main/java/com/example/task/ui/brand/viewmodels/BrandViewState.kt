package com.example.task.ui.brand.viewmodels

import com.example.task.ui.models.BrandResponse

// viewState class to describe screen screen actions to make user restricted to them
sealed class BrandViewState {
    object LOADING : BrandViewState()
    class SUCCESS(val payload: BrandResponse) : BrandViewState()
    class FAILURE(val errorMsg: String) : BrandViewState()
}
