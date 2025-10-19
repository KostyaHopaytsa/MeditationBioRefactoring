package com.example.meditationbiorefactoring.feature_bio.data.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.SivRepository
import com.example.meditationbiorefactoring.feature_bio.data.analyzer.SivAnalyzerCore
import javax.inject.Inject

class SivRepositoryImpl @Inject constructor(
    private val analyzer: SivAnalyzerCore
) : SivRepository {

    override suspend fun startRecording() {
        analyzer.startRecording()
    }

    override suspend fun stopAndAnalyze(): MeasurementAnalysis {
        return analyzer.stopAndAnalyze()
    }

    override suspend fun reset() {
        analyzer.reset()
    }
}