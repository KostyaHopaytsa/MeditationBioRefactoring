package com.example.meditationbiorefactoring.feature_music.domain.use_case

import com.example.meditationbiorefactoring.feature_music.domain.model.Track
import com.example.meditationbiorefactoring.feature_music.domain.repository.TrackRepository
import javax.inject.Inject

class GetTracksByTagUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    suspend operator fun invoke(tag: String): List<Track> {
        return repository.getTracksByTag(tag)
    }
}