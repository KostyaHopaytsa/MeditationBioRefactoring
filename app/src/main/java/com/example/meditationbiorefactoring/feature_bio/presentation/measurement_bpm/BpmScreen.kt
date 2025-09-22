package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementError
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementStart
import com.example.meditationbiorefactoring.feature_bio.presentation.components.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm.components.CameraPreview

@Composable
fun BpmScreen(
    onNavigateToBrpm: () -> Unit,
    viewModel: BpmViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val progress = remember { Animatable(0f) }

    LaunchedEffect(state.isMeasuring) {
        if (state.isMeasuring) {
            progress.snapTo(0f)
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 20000,
                    easing = LinearEasing
                )
            )
        } else {
            progress.snapTo(0f)
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
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    onFrameCaptured = { buffer ->
                        viewModel.onEvent(BpmEvent.FrameCaptured(buffer))
                    }
                )
                LinearProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(25.dp)
                )
            }

            state.isMeasured -> {
                MeasurementResult(
                    status = state.status,
                    value = state.value,
                    type = "BPM",
                    buttonDescription = "To BRPM",
                    onNavigateTo = onNavigateToBrpm,
                    onRestart = { viewModel.onEvent(BpmEvent.Reset) }
                )
            }

            state.error != null -> {
                val errorMessage = when (state.error) {
                    ErrorType.SensorError -> "Camera initialization failed"
                    ErrorType.MeasureError -> "Measurement failed"
                    ErrorType.UnknownError -> "Unknown error"
                }
                MeasurementError(
                    message = errorMessage,
                    onRetry = { viewModel.onEvent(BpmEvent.Retry) }
                )
            }

            else -> {
                MeasurementStart(
                    type = "BPM",
                    onStart = { viewModel.onEvent(BpmEvent.Start) }
                )
            }
        }
    }
}