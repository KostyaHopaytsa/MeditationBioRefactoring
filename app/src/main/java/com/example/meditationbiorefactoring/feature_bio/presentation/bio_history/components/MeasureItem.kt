package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MeasureItem(
    onNavigateTo: () -> Unit,
    date: String,
    bpm: String,
    brpm: String,
    siv: String,
    stress: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RectangleShape,
            )
            .background(color = Color.LightGray)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = date, fontSize = 15.sp)
            Text(text = "BPM: $bpm", fontSize = 15.sp)
            Text(text = "BRPM: $brpm", fontSize = 15.sp)
            Text(text = "SIV: $siv", fontSize = 15.sp)
            Text(text = "Stress: $stress", fontSize = 15.sp)
        }
        IconButton(onClick = onNavigateTo) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "listen",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}