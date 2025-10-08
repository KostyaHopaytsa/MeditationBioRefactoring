package com.example.meditationbiorefactoring.feature_music.domain.use_case

import com.example.meditationbiorefactoring.feature_music.domain.model.Track
import com.example.meditationbiorefactoring.feature_music.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTracksByTagUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    operator fun invoke(tag: String): Flow<List<Track>> {
        return repository.getTracksByTag(tag)
    }
}