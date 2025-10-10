package com.example.meditationbiorefactoring.app.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object BpmScreen : Screen("bpm_screen")
    data object BrpmScreen : Screen("brpm_screen")
    data object SivScreen : Screen("siv_screen")
    data object BioHistoryScreen : Screen("bio_history_screen")
    data object MusicScreen : Screen("music_screen/{stressLevel}/{measurementId}") {
        fun createRoute(stressLevel: String? = null, measurementId: Int? = null): String {
            val safeStress = stressLevel ?: "none"
            val safeId = measurementId?.toString() ?: "0"
            return "music_screen/$safeStress/$safeId"
        }
    }
}