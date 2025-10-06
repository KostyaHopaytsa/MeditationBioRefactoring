package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementAnalysis

interface BrpmRepository {
    suspend fun processFrame(z: Float): MeasurementAnalysis
    suspend fun reset()
}