package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SivScreen(onNavigateToMusic: () -> Unit) {
    Button(onClick = onNavigateToMusic) {
        Text(text = "Listen music based on bio")
    }
}