package com.example.meditationbiorefactoring.feature_bio.presentation.common

sealed class BioEvent {
    data object Start : BioEvent()
    data object Retry : BioEvent()
    data object Reset : BioEvent()
    data class Error(val error: com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType) : BioEvent()
}