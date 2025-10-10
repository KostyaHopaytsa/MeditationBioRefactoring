package com.example.meditationbiorefactoring.feature_bio.domain.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StressMeasurementCollector {
    private val _data = MutableStateFlow(StressData())
    val data: StateFlow<StressData> = _data

    fun setBpm(value: Double) {
        _data.value = _data.value.copy(bpm = value)
    }

    fun setBrpm(value: Double) {
        _data.value = _data.value.copy(brpm = value)
    }

    fun setSiv(value: Double) {
        _data.value = _data.value.copy(siv = value)
    }
}