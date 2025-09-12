package com.example.meditationbiorefactoring.feature_bio.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MeasurementResult(
    status: String,
    value: String,
    type: String,
    buttonDescription: String,
    onNavigateTo: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$type:$value - $status")
            Button(onClick = onNavigateTo) {
                Text(text = buttonDescription)
            }
        }
    }
}