package com.gkuziel.app_compose.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gkuziel.app_compose.presentation.details.DetailsScreen
import com.gkuziel.app_compose.presentation.details.DetailsScreenParams
import com.gkuziel.app_compose.presentation.main.MainScreen
import com.gkuziel.app_compose.presentation.main.MainScreenParams
import androidx.navigation.compose.composable
import androidx.navigation.toRoute


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenParams
    ) {
        composable<MainScreenParams> {
            MainScreen(navController)
        }
        composable<DetailsScreenParams> {
            val args = it.toRoute<DetailsScreenParams>()
            DetailsScreen(
                navController,
                args
            )
        }
    }
}