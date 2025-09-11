package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BrpmScreen(onNavigateToSiv: () -> Unit) {
    Button(onClick = onNavigateToSiv) {
        Text(text = "To SIV")
    }
}