package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData
import javax.inject.Inject

class ComputeOverallStressUseCase @Inject constructor() {
    operator fun invoke(data: StressData): String {
        val bpm = data.bpm ?: return "Unknown"
        val brpm = data.brpm ?: return "Unknown"
        val siv = data.siv ?: return "Unknown"

        var score = 0

        score += when {
            bpm > 90 -> 2
            bpm > 75 -> 1
            else -> 0
        }

        score += when {
            brpm > 20 -> 2
            brpm > 15 -> 1
            else -> 0
        }

        score += when {
            siv > 0.09 -> 2
            siv > 0.03 -> 1
            else -> 0
        }

        return when {
            score <= 1 -> "Low"
            score <= 3 -> "Medium"
            else -> "High"
        }
    }
}