package com.example.meditationbiorefactoring.app.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(
    onNavigateToBpm: () -> Unit,
    onNavigateToMusic: () -> Unit,
    onNavigateToBioHistory: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToBpm) {
            Text(text = "Measurement bio")
        }
        Button(onClick = onNavigateToMusic) {
            Text(text = "Music")
        }
        Button(onClick = onNavigateToBioHistory) {
            Text(text = "Measurement history")
        }
    }
}