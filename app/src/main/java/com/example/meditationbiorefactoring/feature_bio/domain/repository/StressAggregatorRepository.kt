package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.util.StressData
import kotlinx.coroutines.flow.Flow

interface StressAggregatorRepository {
    suspend fun setBpm(value: Double)
    suspend fun setBrpm(value: Double)
    suspend fun setSiv(value: Double)
    fun getData(): Flow<StressData>
    suspend fun reset()
}