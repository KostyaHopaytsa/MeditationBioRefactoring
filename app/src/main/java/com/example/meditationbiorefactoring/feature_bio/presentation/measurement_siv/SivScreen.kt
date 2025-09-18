package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.BioEvent
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementError
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult

@Composable
fun SivScreen(
    onNavigateToMusic: () -> Unit,
    viewModel: SivViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.isMeasuring -> {

            }
            state.isMeasured -> {
                MeasurementResult(
                    status = state.status,
                    value = state.value,
                    type = "SIV",
                    buttonDescription = "To Music",
                    onNavigateTo = onNavigateToMusic,
                    onRestart = { viewModel.onEvent(BioEvent.Reset) }
                )
            }
            state.error != null -> {
                val errorMessage = when (state.error) {
                    ErrorType.SensorError -> "Micro initialization failed"
                    ErrorType.MeasureError -> "Measurement failed"
                    ErrorType.UnknownError -> "Unknown error"
                }
                MeasurementError(
                    message = errorMessage,
                    onRetry = { viewModel.onEvent(BioEvent.Retry) }
                )
            }
            else -> {
                MeasurementStart(
                    type = "SIV",
                    onStart = { viewModel.onEvent(BioEvent.Start) }
                )
            }
        }
    }
}