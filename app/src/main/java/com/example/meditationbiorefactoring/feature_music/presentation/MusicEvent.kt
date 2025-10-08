package com.example.meditationbiorefactoring.feature_music.presentation

import com.example.meditationbiorefactoring.feature_music.domain.model.Track

sealed class MusicEvent {
    data class Play(val track: Track) : MusicEvent()
    data object Pause : MusicEvent()
    data class SeekTo(val progress: Float) : MusicEvent()
    data object Resume : MusicEvent()
    data class OnTrackClick(val track: Track): MusicEvent()
}