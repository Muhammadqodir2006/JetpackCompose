package com.example.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.model.Result
import com.example.jetpackcompose.ui.GameScreen
import com.example.jetpackcompose.ui.HomeScreen
import com.example.jetpackcompose.ui.ResultScreen

@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.Game.route ,
            arguments = listOf(
                navArgument("level"){
                    type = NavType.IntType
                }
            )
            ) { entry ->
            GameScreen(navController, entry.arguments?.getInt("level")!!)
        }
        composable(route = Screen.Result.route,
            arguments = listOf(
                navArgument("correct"){
                    type = NavType.IntType
                },navArgument("incorrect"){
                    type = NavType.IntType
                },navArgument("level"){
                    type = NavType.IntType
                },navArgument("new_record"){
                    type = NavType.BoolType
                },
            )
            ) { entry ->
            val correct = entry.arguments?.getInt("correct")!!
            val incorrect = entry.arguments?.getInt("incorrect")!!
            val level = entry.arguments?.getInt("level")!!
            val newRecord = entry.arguments?.getBoolean("new_record")!!
            ResultScreen(navController = navController, result = Result(correct = correct, incorrect =  incorrect, level = level, newRecord = newRecord))
        }
    }
}