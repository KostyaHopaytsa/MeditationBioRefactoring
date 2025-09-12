package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.runtime.Composable
import com.example.meditationbiorefactoring.feature_bio.presentation.components.Measurement
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun BpmScreen(onNavigateToBrpm: () -> Unit) {
    Measurement(
        type = "BPM",
        onStart = { /* запуск логіки вимірювання */ }
    )
    MeasurementResult(
        status = "low", //must change later to real value
        value = "60", //must change later to real value
        type = "BPM",
        buttonDescription = "To BRPM",
        onNavigateTo = onNavigateToBrpm
    )
}