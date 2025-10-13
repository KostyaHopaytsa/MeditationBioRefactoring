package com.example.meditationbiorefactoring.app.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meditationbiorefactoring.app.presentation.HomeScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.bio_history.BioHistoryScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_bpm.BpmScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_brpm.BrpmScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_siv.SivScreen
import com.example.meditationbiorefactoring.feature_music.presentation.MusicScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onNavigateToBpm = { navController.navigate(Screen.BpmScreen.route) },
                onNavigateToMusic = { navController.navigate(Screen.MusicScreen.createRoute()) },
                onNavigateToBioHistory = { navController.navigate(Screen.BioHistoryScreen.route) }
            )
        }
        composable(route = Screen.BpmScreen.route) {
            BpmScreen(
                onNavigateToBrpm = { navController.navigate(Screen.BrpmScreen.route) }
            )
        }
        composable(route = Screen.BrpmScreen.route) {
            BrpmScreen(
                onNavigateToSiv = { navController.navigate(Screen.SivScreen.route) }
            )
        }
        composable(route = Screen.SivScreen.route) {
            SivScreen(
                onNavigateToMusic = { stressLevel ->
                    navController.navigate(
                        Screen.MusicScreen.createRoute(stressLevel = stressLevel)
                    )
                }
            )
        }
        composable(route = Screen.BioHistoryScreen.route) {
            BioHistoryScreen(
                onNavigateToMusic = { measurementId ->
                    navController.navigate(
                        Screen.MusicScreen.createRoute(measurementId = measurementId)
                    )
                }
            )
        }
        composable(route = Screen.MusicScreen.route,
            arguments = listOf(
                navArgument("stressLevel") {
                    type = NavType.StringType
                    defaultValue = "none"
                },
                navArgument("measurementId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val stressLevel = backStackEntry.arguments?.getString("stressLevel")
            val measurementId = backStackEntry.arguments?.getInt("measurementId")

            MusicScreen(
                stressLevel = if (stressLevel == "none") null else stressLevel,
                measurementId = if (measurementId == 0) null else measurementId
            )

            BackHandler {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) { inclusive = false }
                }
            }
        }
    }
}