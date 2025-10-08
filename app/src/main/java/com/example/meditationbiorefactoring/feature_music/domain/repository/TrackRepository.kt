package com.example.meditationbiorefactoring.feature_music.domain.repository

import com.example.meditationbiorefactoring.feature_music.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getTracksByTag(tag: String): Flow<List<Track>>
}