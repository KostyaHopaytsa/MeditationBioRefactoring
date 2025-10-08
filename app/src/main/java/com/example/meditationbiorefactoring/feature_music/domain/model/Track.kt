package com.example.meditationbiorefactoring.feature_music.domain.model

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val album: String?,
    val imageUrl: String?,
    val audioUrl: String,
)