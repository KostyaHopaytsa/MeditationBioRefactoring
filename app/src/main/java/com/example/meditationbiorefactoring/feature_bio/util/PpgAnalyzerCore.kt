package com.example.meditationbiorefactoring.feature_bio.util

import android.util.Log
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementResult
import javax.inject.Inject

class PpgAnalyzerCore @Inject constructor(){

    private val values = mutableListOf<Double>()
    private val timestamps = mutableListOf<Long>()
    private val maxBufferSize = 200

    fun analyzeFrame(buffer: ByteArray): MeasurementAnalysis {
        val avg = buffer.map { it.toInt() and 0xFF }.average()
        values.add(avg)
        timestamps.add(System.currentTimeMillis())
        Log.d("TEST", "Frame avg=$avg bufferSize=${values.size}")

        if (values.size > maxBufferSize) {
            values.removeAt(0)
            timestamps.removeAt(0)
        }

        val progress = values.size / maxBufferSize.toFloat()

        return if (values.size == maxBufferSize) {
            val bpm = computeBpm(values, timestamps)
            if (bpm in 40..150) {
                Log.d("PpgAnalyzerCore", "BPM=$bpm")
                MeasurementAnalysis (MeasurementResult.Success(bpm), progress = 1f)
            } else {
                Log.w("PpgAnalyzerCore", "Invalid BPM=$bpm")
                MeasurementAnalysis (MeasurementResult.Invalid, progress)
            }
        } else {
            MeasurementAnalysis (MeasurementResult.Error, progress) // ще недостатньо даних
        }
    }

    private fun computeBpm(signal: List<Double>, times: List<Long>): Int {
        val mean = signal.average()
        val normalized = signal.map { it - mean }

        val peaks = normalized
            .windowed(3, 1)
            .count { it[1] > it[0] && it[1] > it[2] && it[1] > 0 }

        val durationSec = (times.last() - times.first()) / 1000.0
        val bpm = (peaks * 60 / durationSec).toInt()
        Log.d("PpgAnalyzerCore", "Peaks=$peaks durationSec=$durationSec bpm=$bpm")
        return bpm
    }

    fun reset() {
        values.clear()
        Log.d("useCase","Use case is run")
    }
}