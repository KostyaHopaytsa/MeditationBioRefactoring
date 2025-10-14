package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_brpm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType
import com.example.meditationbiorefactoring.common.presentation.components.Error
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_brpm.components.BrpmSensorListener
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_siv.SivEvent

@Composable
fun BrpmScreen(
    onNavigateToSiv: () -> Unit,
    viewModel: BrpmViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.navigateEvent.collect {
            onNavigateToSiv()
        }
    }

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
                BrpmSensorListener { z ->
                    viewModel.onEvent(BrpmEvent.DataCaptured(z))
                }
                LinearProgressIndicator(
                    progress = { viewModel.progress.value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(25.dp),
                )
            }
            state.isMeasured -> {
                MeasurementResult(
                    status = state.status,
                    value = state.value,
                    type = "BRPM",
                    buttonDescription = "To SIV",
                    onNavigate =  { viewModel.onEvent(BrpmEvent.NavigateClick) },
                    onRestart = { viewModel.onEvent(BrpmEvent.Retry) }
                )
            }
            state.error != null -> {
                val errorMessage = when (state.error) {
                    ErrorType.SensorError -> "Accelerator initialization failed"
                    ErrorType.MeasureError -> "Measurement failed"
                    ErrorType.UnknownError -> "Unknown error"
                }
                Error(
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