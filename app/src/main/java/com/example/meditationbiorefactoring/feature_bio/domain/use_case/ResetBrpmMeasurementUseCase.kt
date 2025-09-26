package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.BrpmRepository
import javax.inject.Inject

class ResetBrpmMeasurementUseCase @Inject constructor(
    private val repository: BrpmRepository
) {
    suspend operator fun invoke() {
        repository.reset()
    }
}