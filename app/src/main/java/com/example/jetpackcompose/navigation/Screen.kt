package com.example.jetpackcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Game : Screen("game_screen" + "/{level}")
    object Result : Screen("result_screen" + "/{correct}" + "/{incorrect}" + "/{level}")

}