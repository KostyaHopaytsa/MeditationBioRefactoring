package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.MeasurementEvent
import com.example.meditationbiorefactoring.feature_bio.presentation.common.MeasurementState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrpmViewModel @Inject constructor(): ViewModel() {

    private val progressAnim = Animatable(0f)

    private val _state = mutableStateOf(MeasurementState())
    val state: State<MeasurementState> = _state

    fun onEvent(event: MeasurementEvent) {
        when(event) {
            is MeasurementEvent.Start -> startMeasurement()
            is MeasurementEvent.Restart -> restartMeasurement()
            is MeasurementEvent.CameraError -> setError("Gyroscope error: ${event.message}")
            is MeasurementEvent.MeasurementError -> setError("Measurement error: ${event.message}")
        }
    }

    private fun startMeasurement() {
        TODO("Not yet implemented")
    }

    private fun restartMeasurement() {
        TODO("Not yet implemented")
    }

    private fun setError(message: String) {
        TODO("Not yet implemented")
    }
}