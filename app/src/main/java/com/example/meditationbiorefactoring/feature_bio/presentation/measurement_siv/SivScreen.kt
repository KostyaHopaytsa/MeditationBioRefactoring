package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SivScreen(onNavigateToMusic: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "SIV:low")
            Button(onClick = onNavigateToMusic) {
                Text(text = "Listen music based on bio")
            }
        }
    }
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Tap button to start measurement"
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator()
            Button(
                onClick = onNavigateToMusic,
                Modifier
                    .padding(20.dp)
                    .size(75.dp),
                shape = RoundedCornerShape(75.dp),
            ) {
                Text(text = "")
            }
        }
    }
}