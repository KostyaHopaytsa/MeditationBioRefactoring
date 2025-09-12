package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.runtime.Composable
import com.example.meditationbiorefactoring.feature_bio.presentation.components.Measurement
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun BrpmScreen(onNavigateToSiv: () -> Unit) {
    Measurement(
        type = "BRPM",
        onStart = { /* запуск логіки вимірювання */ }
    )
    MeasurementResult(
        status = "low", //must change later to real value
        value = "10", //must change later to real value
        type = "BRPM",
        buttonDescription = "To SIV",
        onNavigateTo = onNavigateToSiv
    )
}