package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement
import com.example.meditationbiorefactoring.feature_bio.domain.repository.MeasurementRepository
import javax.inject.Inject

class InsertMeasurementUseCase @Inject constructor(
    private val repository: MeasurementRepository
) {
    suspend operator fun invoke(measurement: Measurement) {
        repository.insertMeasurement(measurement)
    }
}