package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.StressAggregatorRepository
import javax.inject.Inject

class SetBpmUseCase @Inject constructor(
    private val stressAggregatorRepository: StressAggregatorRepository
) {
    suspend operator fun invoke(bpm: Double) {
        stressAggregatorRepository.setBpm(bpm)
    }
}