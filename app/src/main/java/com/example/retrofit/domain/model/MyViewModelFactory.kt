package com.example.retrofit.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.remote.ListedApi

class MyViewModelFactory(private val apiService: ListedApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}