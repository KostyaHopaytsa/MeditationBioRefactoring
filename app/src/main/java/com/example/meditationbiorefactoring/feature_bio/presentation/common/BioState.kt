package com.example.meditationbiorefactoring.feature_bio.presentation.common

data class BioState(
    val isLoading: Boolean = false,
    val isMeasuring: Boolean = false,
    val isMeasured: Boolean = false,
    val value: String = "",
    val status: String = "",
    val progress: Float = 0f,
    val error: ErrorType? = null
)
