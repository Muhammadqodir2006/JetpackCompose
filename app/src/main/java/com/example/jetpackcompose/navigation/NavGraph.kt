package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackcompose.ui.GameScreen
import com.example.jetpackcompose.ui.HomeScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Game.route + "/{level}",
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