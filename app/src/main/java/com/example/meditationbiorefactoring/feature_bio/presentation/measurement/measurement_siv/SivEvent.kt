package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_siv

import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType

sealed class SivEvent {
    data object Start : SivEvent()
    data object Stop : SivEvent()
    data object Retry : SivEvent()
    data class Error(val error: ErrorType) : SivEvent()
}