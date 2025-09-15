package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.components.Measurement
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun BrpmScreen(
    onNavigateToSiv: () -> Unit,
    viewModel: BrpmViewModel = hiltViewModel()
) {
    Measurement(
        type = "BRPM",
        onStart = { /*start measurement logic*/ }
    )
    MeasurementResult(
        status = "low", //change later to real value
        value = "10", //change later to real value
        type = "BRPM",
        buttonDescription = "To SIV",
        onNavigateTo = onNavigateToSiv,
        onRestart = { /*implement restart logic later*/ }
    )
}