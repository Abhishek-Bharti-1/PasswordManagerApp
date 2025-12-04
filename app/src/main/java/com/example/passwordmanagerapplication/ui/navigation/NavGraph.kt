package com.example.passwordmanagerapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.passwordmanagerapplication.ui.screens.home.HomeScreen

@Composable
fun NavGraph(start: String = "home") {
    val navController = rememberNavController()

    NavHost(navController, start) {
        composable("home") {
            HomeScreen(
                onAddClick = { navController.navigate("add") },
                onItemClick = { }
            )
        }
    }
}