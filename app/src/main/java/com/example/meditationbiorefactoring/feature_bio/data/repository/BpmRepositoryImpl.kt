package com.example.meditationbiorefactoring.feature_bio.data.repository

import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import com.example.meditationbiorefactoring.feature_bio.util.PpgAnalyzerCore
import javax.inject.Inject

class BpmRepositoryImpl @Inject constructor(
    private val analyzer: PpgAnalyzerCore
) : BpmRepository {

    override suspend fun processFrame(buffer: ByteArray): MeasurementAnalysis {
        return analyzer.analyzeFrame(buffer)
    }

    override suspend fun reset() {
        analyzer.reset()
    }
}