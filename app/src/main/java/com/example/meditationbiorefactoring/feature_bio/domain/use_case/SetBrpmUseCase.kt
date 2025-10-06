package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.StressAggregatorRepository
import javax.inject.Inject

class SetBrpmUseCase @Inject constructor(
    private val stressAggregatorRepository: StressAggregatorRepository
) {
    suspend operator fun invoke(brpm: Double) {
        stressAggregatorRepository.setBrpm(brpm)
    }
}