package com.example.meditationbiorefactoring.feature_music.domain.use_case

import com.example.meditationbiorefactoring.feature_music.domain.repository.MusicPlayerRepository
import javax.inject.Inject

class PlayUseCase @Inject constructor(
    private val repository: MusicPlayerRepository
) {
    operator fun invoke(url: String) {
        repository.play(url)
    }
}