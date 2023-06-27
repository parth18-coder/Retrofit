package com.example.retrofit.presentation

import com.example.retrofit.data.remote.ListedDataClass

class ResponseGot{
    var responseGot:ListedDataClass?=null

    fun responseGotFunction(dataResponse:ListedDataClass?){
        responseGot=dataResponse
    }
}