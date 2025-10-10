package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData
import javax.inject.Inject

class ComputeOverallStressUseCase @Inject constructor() {

    operator fun invoke(data: StressData): String {
        val bpm = data.bpm ?: error("BPM not measured yet")
        val brpm = data.brpm ?: error("BRPM not measured yet")
        val siv = data.siv ?: error("SIV not measured yet")

        return when {
            bpm > 100 || brpm > 20 || siv > 0.09 -> "High"
            bpm < 60 || brpm < 12 || siv < 0.03 -> "Low"
            else -> "Medium"
        }
    }
}