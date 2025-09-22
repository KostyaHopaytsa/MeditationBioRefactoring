package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType

sealed class BrpmEvent {
    data object Start : BrpmEvent()
    data object Retry : BrpmEvent()
    data object Reset : BrpmEvent()
    data class Error(val error: ErrorType) : BrpmEvent()
}