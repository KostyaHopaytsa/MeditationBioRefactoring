package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis

interface BrpmRepository {
    suspend fun processFrame(z: Float): MeasurementAnalysis
    suspend fun reset()
}