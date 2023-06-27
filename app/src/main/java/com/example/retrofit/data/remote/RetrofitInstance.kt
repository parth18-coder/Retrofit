package com.example.retrofit.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val token:String="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofits = Retrofit.Builder()
        .baseUrl("https://api.inopenapp.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val listedApi = retrofits.create(ListedApi::class.java)

    fun returnListedApi():ListedApi{
        return listedApi
    }
}