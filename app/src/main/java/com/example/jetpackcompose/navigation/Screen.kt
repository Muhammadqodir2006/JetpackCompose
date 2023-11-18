package com.example.jetpackcompose.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Game : Screen("game_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}