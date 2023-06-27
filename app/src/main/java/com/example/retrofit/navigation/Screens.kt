package com.example.retrofit.navigation

sealed class Screens(val route:String){
    object FirstScreen:Screens("first_screen")
    object MainScreen:Screens("main_screen")
}
