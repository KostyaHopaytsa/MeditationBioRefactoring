package com.example.meditationbiorefactoring.feature_bio.presentation.components

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
fun Measurement(
    type: String,
    onStart: () -> Unit
) {
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Tap button to start $type measurement"
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onStart,
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