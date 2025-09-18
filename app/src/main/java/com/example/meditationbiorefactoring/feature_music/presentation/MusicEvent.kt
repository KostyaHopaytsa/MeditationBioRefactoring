package com.example.meditationbiorefactoring.feature_music.presentation

sealed class MusicEvent {
//    data class SelectTrack(val track: Track) : MusicEvent()
    data object Play : MusicEvent()
    data object Pause : MusicEvent()
    data class SeekTo(val progress: Float) : MusicEvent()
    data object RetryLoad : MusicEvent()
}