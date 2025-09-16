package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.MeasurementState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.animation.core.Animatable
import com.example.meditationbiorefactoring.feature_bio.presentation.common.MeasurementEvent

@HiltViewModel
class BpmViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(MeasurementState())
    val state: State<MeasurementState> = _state

    private val progressAnim = Animatable(0f)

    fun onEvent(event: MeasurementEvent) {
        when(event) {
            is MeasurementEvent.Start -> startMeasurement()
            is MeasurementEvent.Restart -> restartMeasurement()
            is MeasurementEvent.CameraError -> setError("Camera error: ${event.message}")
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