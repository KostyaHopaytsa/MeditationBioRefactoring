package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementAnalysis

interface SivRepository {
    suspend fun startRecording()
    suspend fun stopAndAnalyze(): MeasurementAnalysis
    suspend fun reset()
}