package com.example.passwordmanagerapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.passwordmanagerapplication.ui.screens.auth.BiometricScreen
import com.example.passwordmanagerapplication.ui.screens.home.HomeScreen

@Composable
fun NavGraph(start: String = "auth") {
    val navController = rememberNavController()

    NavHost(navController, start) {
        composable("auth") {
            BiometricScreen(
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                onAddClick = { navController.navigate("add") },
                onItemClick = { }
            )
        }
    }
}