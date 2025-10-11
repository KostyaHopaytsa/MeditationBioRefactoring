package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData
import javax.inject.Inject

class ComputeOverallStressUseCase @Inject constructor() {

    operator fun invoke(data: StressData): String {
        val bpm = data.bpm ?: return "Unknown"
        val brpm = data.brpm ?: return "Unknown"
        val siv = data.siv ?: return "Unknown"

        return when {
            bpm > 100 || brpm > 20 || siv > 0.09 -> "High"
            bpm < 60 || brpm < 12 || siv < 0.03 -> "Low"
            else -> "Medium"
        }
    }
}