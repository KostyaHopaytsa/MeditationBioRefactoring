package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.bio_history.components.MeasureItem

@Composable
fun BioHistoryScreen(
    onNavigateToMusic: () -> Unit,
    viewModel: BioHistoryViewModel = hiltViewModel()
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(10) {
            //all string hardcoded and must be implemented later
            MeasureItem(
                onNavigateTo = onNavigateToMusic,
                date = "12.09.2025 11:00",
                bpm = "60 - low",
                brpm = "10 - low",
                siv = "20 - low",
                stress = "low"
            )
        }
    }
}