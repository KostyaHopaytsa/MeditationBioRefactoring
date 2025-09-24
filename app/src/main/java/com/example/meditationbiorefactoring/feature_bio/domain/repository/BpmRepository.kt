package com.example.meditationbiorefactoring.feature_bio.domain.repository

import com.example.meditationbiorefactoring.feature_bio.domain.model.BpmAnalysis

interface BpmRepository {
    suspend fun processFrame(buffer: ByteArray): BpmAnalysis
}