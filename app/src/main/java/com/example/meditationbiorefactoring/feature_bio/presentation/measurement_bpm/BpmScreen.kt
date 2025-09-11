package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

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
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResultSection

@Composable
fun BpmScreen(onNavigateToBrpm: () -> Unit) {
    MeasurementResultSection(
        measurementResult = "low", //must change later to real value
        measurementResultValue = "60", //must change later to real value
        measurementType = "BPM",
        nextMeasurementType = "BRPM",
        onNavigateTo = onNavigateToBrpm
    )
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
                onClick = onNavigateToBrpm,
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