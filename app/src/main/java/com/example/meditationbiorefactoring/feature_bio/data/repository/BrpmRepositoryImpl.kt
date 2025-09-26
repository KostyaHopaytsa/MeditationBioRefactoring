package com.example.meditationbiorefactoring.feature_bio.data.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BrpmRepository
import com.example.meditationbiorefactoring.feature_bio.util.BreathAnalyzerCore
import javax.inject.Inject

class BrpmRepositoryImpl @Inject constructor(
    private val analyzer: BreathAnalyzerCore
) : BrpmRepository {

    override suspend fun processFrame(z: Float): MeasurementAnalysis {
        return analyzer.processFrame(z)
    }

    override suspend fun reset() {
        analyzer.reset()
    }
}
