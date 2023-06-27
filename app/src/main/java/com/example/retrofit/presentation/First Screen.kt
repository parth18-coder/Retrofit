package com.example.retrofit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit.data.remote.ListedDataClass
import com.example.retrofit.data.remote.MakeApiRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ScreenFirstUi(){

    var result by remember { mutableStateOf("") }

    var responseGotObj=ResponseGot()

    Column(modifier = Modifier.fillMaxSize()) {

        Button(onClick = { GlobalScope.launch {
            MakeApiRequest.makeApiRequest() { response ->
                result = when {
                    response.isSuccessful -> {
                        var dataResponse = response.body()

                        responseGotObj.responseGotFunction(dataResponse)

                        var recentLinksArray = dataResponse?.data?.recentLinks
                        recentLinksArray?.get(0)?.webLink ?: "No data available"
                    }
                    else -> "Error: ${response.code()}"
                }
            }
        } },
            modifier = Modifier.padding(16.dp)){
            Text(text = "Fetch Data")
        }

        Button(onClick = { }) {
            Text(text = "Next Screen")

        }

    }
}
