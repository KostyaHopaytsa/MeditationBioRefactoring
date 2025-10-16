package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_brpm

sealed class BrpmEvent {
    data object Start : BrpmEvent()
    data object Retry : BrpmEvent()
    data class DataCaptured (val z: Float) : BrpmEvent()
    data object NavigateClick : BrpmEvent()
}