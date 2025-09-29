package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_siv

import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType

sealed class SivEvent {
    data object Start : SivEvent()
    data object Stop : SivEvent()
    data object Retry : SivEvent()
    data class Error(val error: ErrorType) : SivEvent()
}