package com.example.meditationbiorefactoring.feature_bio.data.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.BpmAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.repository.BpmRepository
import com.example.meditationbiorefactoring.feature_bio.util.PpgAnalyzerCore

class BpmRepositoryImpl : BpmRepository {

    private val analyzer = PpgAnalyzerCore()

    override suspend fun processFrame(buffer: ByteArray): BpmAnalysis {
        return analyzer.analyzeFrame(buffer)
    }
}