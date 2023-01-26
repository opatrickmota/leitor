package com.patrickmota.leitor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.patrickmota.leitor.screens.home.HomeScreen
import com.patrickmota.leitor.screens.reader.ReaderScreen
import com.patrickmota.leitor.screens.splash.SplashScreen

@Composable
fun LeitorNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LeitorScreens.SplashScreen.name) {
        composable(LeitorScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(LeitorScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(
            "${LeitorScreens.ReaderScreen.name}/{url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ReaderScreen(
                navController,
                backStackEntry.arguments?.getString("url").toString()
            )
        }
    }
}