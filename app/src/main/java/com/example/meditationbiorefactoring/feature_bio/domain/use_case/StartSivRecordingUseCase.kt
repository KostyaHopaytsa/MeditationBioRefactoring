package com.example.meditationbiorefactoring.feature_bio.domain.use_case

import com.example.meditationbiorefactoring.feature_bio.domain.repository.SivRepository
import javax.inject.Inject

class StartSivRecordingUseCase @Inject constructor(
    private val repository: SivRepository
) {
    suspend operator fun invoke() {
        repository.startRecording()
    }
}