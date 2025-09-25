package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import javax.inject.Inject

class ResetBpmMeasurementUseCase @Inject constructor(
    private val repository: BpmRepository
) {
    suspend operator fun invoke() {
        repository.reset()
    }
}