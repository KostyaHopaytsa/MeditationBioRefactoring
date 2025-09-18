package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement
import kotlinx.coroutines.flow.Flow

interface MeasurementRepository {

    fun getMeasurements(): Flow<List<Measurement>>

    suspend fun getMeasurementById(id: Int): Measurement?

    suspend fun insertMeasurement(measurement: Measurement)

    suspend fun deleteMeasurement(measurement: Measurement)
}