package com.example.meditationbiorefactoring.feature_bio.data.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement
import com.example.meditationbiorefactoring.feature_bio.domain.repository.MeasurementRepository
import com.example.meditationbiorefactoring.feature_bio.domain.repository.StressAggregatorRepository
import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class StressAggregatorRepositoryImpl @Inject constructor(
    private val measurementRepository: MeasurementRepository
) : StressAggregatorRepository {
    private val _data = MutableStateFlow(StressData())
    override fun getData(): Flow<StressData> = _data

    override suspend fun setBpm(value: Double) {
        _data.value = _data.value.copy(bpm = value)
        checkAndInsert()
    }

    override suspend fun setBrpm(value: Double) {
        _data.value = _data.value.copy(brpm = value)
        checkAndInsert()
    }

    override suspend fun setSiv(value: Double) {
        _data.value = _data.value.copy(siv = value)
        checkAndInsert()
    }

    override suspend fun reset() {
        _data.value = StressData()
    }

    private suspend fun checkAndInsert() {
        val data = _data.value
        if (data.isComplete()) {
            val stressLevel = computeOverallStress(data)
            measurementRepository.insertMeasurement(
                Measurement(
                    timestamp = System.currentTimeMillis(),
                    bpm = data.bpm!!,
                    brpm = data.brpm!!,
                    siv = data.siv!!,
                    stress = stressLevel,
                )
            )
        }
    }

    private fun computeOverallStress(data: StressData): String {
        return when {
            data.bpm!! > 100 || data.brpm!! > 20 || data.siv!! > 0.09 -> "High"
            data.bpm < 60 || data.brpm < 12 || data.siv < 0.03 -> "Low"
            else -> "Medium"
        }
    }
}