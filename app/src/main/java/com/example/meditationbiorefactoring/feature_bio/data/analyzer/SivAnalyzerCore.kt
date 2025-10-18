package com.example.meditationbiorefactoring.feature_bio.data.analyzer

import android.media.AudioFormat
import android.media.AudioRecord
import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementResult
import android.media.MediaRecorder
import kotlin.math.pow

class SivAnalyzerCore {
    private val sampleRate = 16000
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT

    private var recorder: AudioRecord? = null
    private lateinit var buffer: ShortArray
    private var bufferSize: Int = 0
    private var currentIndex = 0
    private var isRecording = false

    fun startRecording() {
        try {
            bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
            if (bufferSize <= 0) return
            buffer = ShortArray(bufferSize * 5)

            recorder = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                sampleRate,
                channelConfig,
                audioFormat,
                bufferSize
            )

            if (recorder?.state != AudioRecord.STATE_INITIALIZED) return

            isRecording = true
            currentIndex = 0
            recorder?.startRecording()

            Thread {
                while (isRecording && currentIndex < buffer.size) {
                    val read = recorder?.read(buffer, currentIndex, buffer.size - currentIndex) ?: 0
                    if (read > 0) currentIndex += read
                }
            }.start()
        } catch (_: SecurityException) {
            isRecording = false
            recorder?.release()
            recorder = null
        }
    }

    fun stopAndAnalyze(): MeasurementAnalysis {
        if (!isRecording || recorder == null) {
            return MeasurementAnalysis(MeasurementResult.Error, 0f)
        }
        if (currentIndex <= 0) {
            return MeasurementAnalysis(MeasurementResult.Error, 1f)
        }

        isRecording = false
        try {
            recorder?.stop()
        } catch (_: IllegalStateException) { }
        recorder?.release()
        recorder = null

        val result = analyze(buffer, currentIndex)
        return MeasurementAnalysis(MeasurementResult.Success(result), progress = 1f)
    }

    fun reset() {
        currentIndex = 0
        isRecording = false
        buffer = ShortArray(0)
    }

    private fun analyze(buffer: ShortArray, length: Int): Double {
        var sumSq = 0.0
        var maxAmp = 0.0
        val norm = DoubleArray(length)

        for (i in 0 until length) {
            norm[i] = buffer[i] / 32768.0
            sumSq += norm[i] * norm[i]
            if (kotlin.math.abs(norm[i]) > maxAmp) maxAmp = kotlin.math.abs(norm[i])
        }

        val rms = kotlin.math.sqrt(sumSq / length)
        val mean = sumSq / length
        var std = 0.0
        for (v in norm) std += (v * v - mean).pow(2)
        std = kotlin.math.sqrt(std / length)

        return rms + std
    }
}