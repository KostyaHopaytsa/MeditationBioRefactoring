package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import androidx.compose.runtime.Composable
import com.example.meditationbiorefactoring.feature_bio.presentation.components.Measurement
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun SivScreen(onNavigateToMusic: () -> Unit) {
    Measurement(
        type = "SIV",
        onStart = { /* запуск логіки вимірювання */ }
    )
    MeasurementResult(
        status = "low", //must change later to real value
        value = "20", //must change later to real value
        type = "SIV",
        buttonDescription = "Listen music",
        onNavigateTo = onNavigateToMusic
    )
}