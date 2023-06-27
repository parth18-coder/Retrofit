package com.example.retrofit.domain.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.remote.ListedApi
import com.example.retrofit.data.remote.ListedDataClass
import kotlinx.coroutines.launch

class MyViewModel(apiService:ListedApi): ViewModel() {
    private val _dataState = mutableStateOf<ListedDataClass?>(null)
    val dataState: State<ListedDataClass?> = _dataState

    val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    init {
        fetchData(apiService)
    }

    private fun fetchData(apiService: ListedApi) {
        viewModelScope.launch {
            val response = apiService.getListing(token)
            if (response.isSuccessful) {
                _dataState.value = response.body()
            } else {
                // Handle error
            }
        }
    }


}