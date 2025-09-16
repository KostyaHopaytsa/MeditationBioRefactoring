package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementError
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun BpmScreen(
    onNavigateToBrpm: () -> Unit,
    viewModel: BpmViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    MeasurementStart(
        type = "BPM",
        onStart = { /*start measurement logic*/ }
    )
    MeasurementResult(
        status = "low", //change later to real value
        value = "60", //change later to real value
        type = "BPM",
        buttonDescription = "To BRPM",
        onNavigateTo = onNavigateToBrpm,
        onRestart = { /*implement restart logic later*/ }
    )
    MeasurementError(
        message = "error",
        onRetry = { TODO() }
    )
}