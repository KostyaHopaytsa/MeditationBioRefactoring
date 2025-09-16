package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun SivScreen(
    onNavigateToMusic: () -> Unit,
    viewModel: SivViewModel = hiltViewModel()
) {
    MeasurementStart(
        type = "SIV",
        onStart = { /*start measurement logic*/ }
    )
    MeasurementResult(
        status = "low", //change later to real value
        value = "20", //change later to real value
        type = "SIV",
        buttonDescription = "Listen music",
        onNavigateTo = onNavigateToMusic,
        onRestart = { /*implement restart logic later*/ }
    )
}