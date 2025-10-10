package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.bio_history.components.MeasureItem

@Composable
fun BioHistoryScreen(
    onNavigateToMusic: (Int) -> Unit,
    viewModel: BioHistoryViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.measurements) { measurement ->
            MeasureItem(
                onNavigateTo = {
                    measurement.id?.let{
                    onNavigateToMusic(it) }
                },
                measurement = measurement
            )
        }
    }
}