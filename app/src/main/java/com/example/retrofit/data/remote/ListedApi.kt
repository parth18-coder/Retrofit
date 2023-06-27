package com.example.retrofit.data.remote

import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ListedApi {

    @GET("api/v1/dashboardNew")
    suspend fun getListing(
        @Header("Authorization") token: String
    ): Response<ListedDataClass>

}