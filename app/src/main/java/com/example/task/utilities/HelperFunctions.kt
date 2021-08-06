package com.example.task.utilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * generic function that returns viewModel instance that customized there parameters using ViewModelProvider factory
 * used for creating instance of viewModel with a passing parameters to it
 * */
inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
    }
