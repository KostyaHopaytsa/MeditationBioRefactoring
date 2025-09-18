package com.example.meditationbiorefactoring.feature_bio.presentation.common

sealed class ErrorType {
    data object SensorError : ErrorType()
    data object MeasureError : ErrorType()
    data object UnknownError : ErrorType()
}