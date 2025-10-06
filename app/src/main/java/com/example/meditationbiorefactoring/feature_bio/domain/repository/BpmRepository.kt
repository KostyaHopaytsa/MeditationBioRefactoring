package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementAnalysis

interface BpmRepository {
    suspend fun processFrame(buffer: ByteArray): MeasurementAnalysis
    suspend fun reset()
}