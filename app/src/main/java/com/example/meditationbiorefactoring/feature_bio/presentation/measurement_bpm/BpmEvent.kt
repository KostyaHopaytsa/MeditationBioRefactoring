package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType

sealed class BpmEvent {
    data object Start : BpmEvent()
    data object Retry : BpmEvent()
    data object Reset : BpmEvent()
    data class Error(val error: ErrorType) : BpmEvent()
    data class FrameCaptured(val buffer: ByteArray) : BpmEvent()
}