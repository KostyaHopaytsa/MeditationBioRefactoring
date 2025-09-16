package com.example.meditationbiorefactoring.feature_bio.presentation.common

sealed class MeasurementEvent {
    data object Start : MeasurementEvent()
    data object Restart : MeasurementEvent()
    data class CameraError(val message: String) : MeasurementEvent()
    data class MeasurementError(val message: String) : MeasurementEvent()
}