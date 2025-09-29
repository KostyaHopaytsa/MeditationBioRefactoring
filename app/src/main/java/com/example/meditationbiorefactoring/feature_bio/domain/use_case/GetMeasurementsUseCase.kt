package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement
import com.example.meditationbiorefactoring.feature_bio.domain.repository.MeasurementRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMeasurementsUseCase @Inject constructor(
    private val repository: MeasurementRepository
) {
    operator fun invoke(): Flow<List<Measurement>> {
        return repository.getMeasurements()
    }
}