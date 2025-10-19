package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.SivRepository
import javax.inject.Inject

class StopSivAndAnalyzeUseCase @Inject constructor(
    private val repository: SivRepository
) {
    suspend operator fun invoke(): MeasurementAnalysis {
        return repository.stopAndAnalyze()
    }
}