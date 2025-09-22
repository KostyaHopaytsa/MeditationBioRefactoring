package com.example.meditationbiorefactoring.feature_bio.util

object SignalProcessingUtils {

    fun smoothEma(
        signal: ByteArray,
        alpha: Double = 0.1
    ): List<Double> {
        if (signal.isEmpty()) return emptyList()
        val result = MutableList(signal.size) { 0.0 }
        result[0] = signal[0].toDouble()
        for (i in 1 until signal.size) {
            result[i] = alpha * signal[i] + (1 - alpha) * result[i - 1]
        }
        return result
    }

    fun normalize(signal: List<Double>): List<Double> {
        if (signal.isEmpty()) return emptyList()
        val mean = signal.average()
        return signal.map { it - mean }
    }

    fun bandpass(signal: List<Double>): List<Double> {
        if (signal.size < 3) return signal
        val result = MutableList(signal.size) { 0.0 }
        for (i in 1 until signal.size - 1) {
            result[i] = signal[i] - 0.5 * (signal[i - 1] + signal[i + 1])
        }
        return result
    }

    fun countPeaks(
        signal: List<Double>,
        minDistance: Int = 6,
        thresholdMultiplier: Double = 0.8
    ): Int {
        if (signal.isEmpty()) return 0
        val mean = signal.average()
        val std = kotlin.math.sqrt(signal.map { (it - mean) * (it - mean) }.average())
        val threshold = mean + std * thresholdMultiplier

        var peaks = 0
        var i = minDistance
        while (i < signal.size - minDistance) {
            var isPeak = signal[i] > threshold
            for (j in 1..minDistance) {
                if (signal[i] <= signal[i - j] || signal[i] <= signal[i + j]) {
                    isPeak = false
                    break
                }
            }
            if (isPeak) {
                peaks++
                i += minDistance
            }
            i++
        }
        return peaks
    }
}