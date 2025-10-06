package com.example.meditationbiorefactoring.feature_bio.domain.util

sealed class MeasurementResult {
    data class Success(val value: Double) : MeasurementResult()
    data object Invalid : MeasurementResult()
    data object Error : MeasurementResult()
}