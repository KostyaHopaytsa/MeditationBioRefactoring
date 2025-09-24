package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.model.BpmAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import javax.inject.Inject

class BpmMeasurementUseCase @Inject constructor(
    private val repository: BpmRepository
) {
    suspend operator fun invoke(buffer: ByteArray): BpmAnalysis {
        return repository.processFrame(buffer)
    }
}