package com.example.meditationbiorefactoring.feature_bio.presentation.measurement

import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData

data class MeasurementState(
    val stressData: StressData = StressData(),
    val overallStress: String = ""
)

