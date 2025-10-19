package com.example.meditationbiorefactoring.feature_bio.data.analyzer

import android.media.AudioFormat
import android.media.AudioRecord
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementAnalysis
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementResult
import android.media.MediaRecorder
import com.example.meditationbiorefactoring.feature_bio.domain.util.SignalProcessing
import kotlin.math.pow
import kotlin.math.sqrt

class SivAnalyzerCore {
    private val sampleRate = 16000
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    private var recorder: AudioRecord? = null
    private lateinit var buffer: ShortArray
    private var bufferSize = 0
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
        if (!isRecording || recorder == null) return MeasurementAnalysis(MeasurementResult.Error, 0f)
        if (currentIndex <= 0) return MeasurementAnalysis(MeasurementResult.Error, 1f)

        isRecording = false
        try {
            recorder?.stop()
        } catch (_: IllegalStateException) {}
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
        val norm = buffer.take(length).map { it / 32768.0 }.toDoubleArray()
        val normalized = SignalProcessing.normalize(norm)
        val rms = sqrt(normalized.map { it * it }.average())
        val mean = normalized.average()
        val std = sqrt(normalized.map { (it - mean).pow(2) }.average())
        return rms + std
    }
}