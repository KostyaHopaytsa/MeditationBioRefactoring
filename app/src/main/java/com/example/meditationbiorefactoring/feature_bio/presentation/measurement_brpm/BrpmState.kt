package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType

data class BrpmState(
    val isLoading: Boolean = false,
    val isMeasuring: Boolean = false,
    val isMeasured: Boolean = false,
    val value: String = "",
    val status: String = "",
    val progress: Float = 0f,
    val error: ErrorType? = null
)
