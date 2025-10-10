package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_brpm

import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType

sealed class BrpmEvent {
    data object Start : BrpmEvent()
    data object Retry : BrpmEvent()
    data class Error(val error: ErrorType) : BrpmEvent()
    data class DataCaptured (val z: Float) : BrpmEvent()
}