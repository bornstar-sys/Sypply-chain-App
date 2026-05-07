package com.cardano.prototype1.ui.navigation

sealed class Screen(val route:String){
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object AddMedicine : Screen("add_medicine")
    object Scan : Screen("scan")
    object Result : Screen("result/{data}") {
        fun createRoute(data: String) = "result/$data"
    }
}