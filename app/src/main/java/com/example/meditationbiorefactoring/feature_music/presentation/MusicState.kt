package com.example.meditationbiorefactoring.feature_music.presentation

data class MusicState(
    val tracks: List<Track> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentTrack: Track? = null,
    val isPlaying: Boolean = false,
    val progress: Float = 0f
)
