package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis

interface BpmRepository {
    suspend fun processFrame(buffer: ByteArray): MeasurementAnalysis
    suspend fun reset()
}