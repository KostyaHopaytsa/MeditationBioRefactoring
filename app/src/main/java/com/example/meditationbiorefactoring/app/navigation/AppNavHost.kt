package com.example.meditationbiorefactoring.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditationbiorefactoring.app.presentation.HomeScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm.BpmScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm.BrpmScreen
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv.SivScreen
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
                onNavigateToMusic = { navController.navigate(Screen.MusicScreen.route) }
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
                onNavigateToMusic = { navController.navigate(Screen.MusicScreen.route) }
            )
        }
        composable(route = Screen.MusicScreen.route) {
            MusicScreen()
        }
    }
}