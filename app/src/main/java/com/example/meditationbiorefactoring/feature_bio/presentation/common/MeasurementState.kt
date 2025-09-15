package com.example.meditationbiorefactoring.feature_bio.presentation.common

data class MeasurementState(
    val value: String = "",
    val status: String = "",
    val isMeasuring: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val progress: Float = 0f,
)
