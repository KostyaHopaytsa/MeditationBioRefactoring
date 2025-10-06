package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.StressAggregatorRepository
import javax.inject.Inject

class SetSivUseCase @Inject constructor(
    private val stressAggregatorRepository: StressAggregatorRepository
) {
    suspend operator fun invoke(siv: Double) {
        stressAggregatorRepository.setSiv(siv)
    }
}