package com.example.meditationbiorefactoring.feature_music.domain.repository

interface MusicPlayerRepository {
    fun play(url: String)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(positionMs: Long)
    fun getCurrentPosition(): Long
    fun getDuration(): Long
    fun isPlaying(): Boolean
}