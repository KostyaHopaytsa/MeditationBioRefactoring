package com.example.meditationbiorefactoring.feature_bio.util

import com.example.meditationbiorefactoring.feature_bio.domain.model.BpmResult

class PpgAnalyzerCore {

    private val values = mutableListOf<Double>()
    private val maxBufferSize = 600 // ~20 сек @30fps

    fun analyzeFrame(buffer: ByteArray): BpmResult {
        val avg = buffer.map { it.toInt() and 0xFF }.average()
        values.add(avg)
        if (values.size > maxBufferSize) values.removeAt(0)

        return if (values.size == maxBufferSize) {
            val bpm = computeBpm(values)
            if (bpm in 40..150) BpmResult.Success(bpm) else BpmResult.Invalid
        } else {
            BpmResult.Error // ще недостатньо даних
        }
    }

    private fun computeBpm(signal: List<Double>): Int {
        // мінімальна обробка, можна винести в SignalProcessingUtils
        val mean = signal.average()
        val normalized = signal.map { it - mean }

        val peaks = normalized
            .windowed(3, 1)
            .count { it[1] > it[0] && it[1] > it[2] && it[1] > 0 }

        val durationSec = signal.size / 30.0
        return (peaks * 60 / durationSec).toInt()
    }
}