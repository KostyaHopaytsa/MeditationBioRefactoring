package com.example.meditationbiorefactoring.feature_bio.util

import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementResult
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

class BreathAnalyzerCore @Inject constructor() {
    private val zValues = mutableListOf<Float>()
    private val bufferSize = 1200 // ~20 сек при 60 Гц
    private val smoothingWindow = 20
    private val minPeakDistance = 30
    private val minPeakAmplitude = 0.007f

    fun processFrame(z: Float): MeasurementAnalysis {
        zValues.add(z)
        if (zValues.size > bufferSize) zValues.removeAt(0)

        val progress = zValues.size.toFloat() / bufferSize.toFloat()

        return if (zValues.size == bufferSize) {
            val brpm = computeBrpm(zValues)
            if (brpm in 5..40) {
                MeasurementAnalysis(MeasurementResult.Success(brpm), 1f)
            } else {
                MeasurementAnalysis(MeasurementResult.Invalid, progress)
            }
        } else {
            MeasurementAnalysis(MeasurementResult.Error, progress)
        }
    }

    fun reset() {
        zValues.clear()
    }

    private fun computeBrpm(values: List<Float>): Int {
        // Згладжування ковзним середнім
        val smooth = mutableListOf<Float>()
        for (i in values.indices) {
            val start = max(0, i - smoothingWindow)
            val end = min(values.size - 1, i + smoothingWindow)
            val avg = values.subList(start, end + 1).average().toFloat()
            smooth.add(avg)
        }

        // Пошук піків
        var peaks = 0
        var lastPeak = -minPeakDistance
        for (i in 1 until smooth.size - 1) {
            val current = smooth[i]
            val prev = smooth[i - 1]
            val next = smooth[i + 1]

            if (current > prev && current > next) {
                if ((i - lastPeak) > minPeakDistance) {
                    val localMin = min(prev, next)
                    val amplitude = current - localMin
                    if (amplitude >= minPeakAmplitude) {
                        peaks++
                        lastPeak = i
                    }
                }
            }
        }

        return (peaks * 60.0 / (bufferSize / 30.0)).toInt()
    }
}