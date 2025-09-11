package com.example.meditationbiorefactoring.feature_bio.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MeasurementResultSection(
    measurementResult: String,
    measurementResultValue: String,
    measurementType: String,
    nextMeasurementType: String,
    onNavigateTo: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$measurementType:$measurementResultValue - $measurementResult")
            Button(onClick = onNavigateTo) {
                Text(text = "To $nextMeasurementType")
            }
        }
    }
}