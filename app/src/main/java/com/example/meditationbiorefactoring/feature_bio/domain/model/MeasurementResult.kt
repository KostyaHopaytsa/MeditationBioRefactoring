package com.example.meditationbiorefactoring.feature_bio.domain.model

sealed class MeasurementResult {
    data class Success(val value: Double) : MeasurementResult()
    data object Invalid : MeasurementResult()
    data object Error : MeasurementResult()
}