package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_bpm

import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType

sealed class BpmEvent {
    data object Start : BpmEvent()
    data object Retry : BpmEvent()
    data class Error(val error: ErrorType) : BpmEvent()
    data class FrameCaptured(val buffer: ByteArray) : BpmEvent()
}