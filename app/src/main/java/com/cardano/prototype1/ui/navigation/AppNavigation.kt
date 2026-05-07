package com.cardano.prototype1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cardano.prototype1.ui.screens.DashboardScreen
import com.cardano.prototype1.ui.screens.LoginScreen
import com.cardano.prototype1.ui.screens.ResultScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Login.route){

//        composable(Screen.Login.route) {
//            LoginScreen(
//                onLoginClick = {
//                    navController.navigate(Screen.Dashboard.route)
//                }
//            )
//        }
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable("result/{data}") { backStackEntry ->
            val data = backStackEntry.arguments?.getString("data")
            ResultScreen(data)
        }

    }
}