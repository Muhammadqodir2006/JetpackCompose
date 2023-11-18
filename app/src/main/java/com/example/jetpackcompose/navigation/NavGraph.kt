package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.ui.GameScreen
import com.example.jetpackcompose.ui.HomeScreen

@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.Game.route + "/{level}",
            arguments = listOf(
                navArgument("level"){
                    type = NavType.StringType
                    nullable = false
                }
            )
            ) { entry ->
            GameScreen(navController, entry.arguments?.getString("level")!!)
        }
    }
}