package com.example.retrofit.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrofit.presentation.MainScreenUi
import com.example.retrofit.presentation.ScreenFirstUi

@Composable
@ExperimentalMaterialApi
fun Navigation(){
    var navController= rememberNavController()

    NavHost(
        navController=navController,
        startDestination = Screens.FirstScreen.route
    ){
        composable(route = Screens.FirstScreen.route){
            ScreenFirstUi()
        }

        composable(route = Screens.MainScreen.route){
            MainScreenUi()
        }
    }
}