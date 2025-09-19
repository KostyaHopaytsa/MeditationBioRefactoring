package com.example.meditationbiorefactoring.feature_music.data.repository

import com.example.meditationbiorefactoring.common.Constants
import com.example.meditationbiorefactoring.feature_music.data.remote.JamendoApi
import com.example.meditationbiorefactoring.feature_music.data.remote.dto.toTrack
import com.example.meditationbiorefactoring.feature_music.domain.model.Track
import com.example.meditationbiorefactoring.feature_music.domain.repository.TrackRepository

class TrackRepositoryImpl(
    private val api: JamendoApi
) : TrackRepository {
    override suspend fun getTracksByTag(tag: String): List<Track> {
        return api.getTracksByTag(
            clientId = Constants.CLIENT_ID,
            tag = tag
        ).results.map { it.toTrack() }
    }
}
