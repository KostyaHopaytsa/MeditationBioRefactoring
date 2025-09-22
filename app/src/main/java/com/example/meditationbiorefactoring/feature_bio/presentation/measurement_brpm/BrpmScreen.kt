package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementError
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm.BpmEvent

@Composable
fun BrpmScreen(
    onNavigateToSiv: () -> Unit,
    viewModel: BrpmViewModel = hiltViewModel()
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
                    type = "BRPM",
                    buttonDescription = "To SIV",
                    onNavigateTo = onNavigateToSiv,
                    onRestart = { viewModel.onEvent(BrpmEvent.Reset) }
                )
            }
            state.error != null -> {
                val errorMessage = when (state.error) {
                    ErrorType.SensorError -> "Accelerator initialization failed"
                    ErrorType.MeasureError -> "Measurement failed"
                    ErrorType.UnknownError -> "Unknown error"
                }
                MeasurementError(
                    message = errorMessage,
                    onRetry = { viewModel.onEvent(BrpmEvent.Retry) }
                )
            }
            else -> {
                MeasurementStart(
                    type = "BRPM",
                    onStart = { viewModel.onEvent(BrpmEvent.Start) }
                )
            }
        }
    }
}