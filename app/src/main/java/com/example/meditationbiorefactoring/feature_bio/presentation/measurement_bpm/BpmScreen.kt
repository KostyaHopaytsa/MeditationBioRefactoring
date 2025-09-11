package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BpmScreen(onNavigateToBrpm: () -> Unit) {
    Button(onClick = onNavigateToBrpm) {
        Text(text = "To BRPM")
    }
}