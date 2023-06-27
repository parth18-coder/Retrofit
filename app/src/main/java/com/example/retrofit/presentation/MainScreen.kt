package com.example.retrofit.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.retrofit.data.remote.ListedDataClass

@Composable
fun MainScreenUi(){
    Column(modifier = Modifier.fillMaxSize()) {
        ChartCanvas()
        TodayRow()
        
    }
    
}

@Composable
fun ChartCanvas(){
    Box(modifier = Modifier
        .height(200.dp)
        .border(border = BorderStroke(2.dp, Color.Black))
        .fillMaxWidth()) {
        
    }
}

@Composable
fun TodayRow(){
    
    var responseGotObj=ResponseGot()
    
    Row(modifier= Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        Text(text = responseGotObj?.responseGot?.todayClicks.toString()?:"UnSuccessful Call ")
        Text(text = responseGotObj?.responseGot?.topLocation?:"UnSuccessful Call ")
        Text(text = responseGotObj?.responseGot?.topSource?:"UnSuccessful Call ")
    }
}