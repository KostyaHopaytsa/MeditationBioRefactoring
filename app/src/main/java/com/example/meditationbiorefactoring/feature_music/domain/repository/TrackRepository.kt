package com.example.meditationbiorefactoring.feature_music.domain.repository

import com.example.meditationbiorefactoring.feature_music.domain.model.Track

interface TrackRepository {
    suspend fun getTracksByTag(tag: String): List<Track>
}