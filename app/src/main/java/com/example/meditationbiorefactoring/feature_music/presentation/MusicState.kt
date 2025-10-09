package com.example.meditationbiorefactoring.feature_music.presentation

import com.example.meditationbiorefactoring.feature_music.domain.model.Track

data class MusicState(
    val tracks: List<Track> = emptyList(),
    val isLoading: Boolean = false,
    val currentTrack: Track? = null,
    val isPlaying: Boolean = false,
    val progress: Float = 0f
)
