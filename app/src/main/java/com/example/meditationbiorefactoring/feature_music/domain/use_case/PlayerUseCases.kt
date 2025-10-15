package com.example.meditationbiorefactoring.feature_music.domain.use_case

class PlayerUseCases (
    val playUseCase: PlayUseCase,
    val pauseUseCase: PauseUseCase,
    val resumeUseCase: ResumeUseCase,
    val getCurrentPositionUseCase: GetCurrentPositionUseCase,
    val getDurationUseCase: GetDurationUseCase,
    val isPlayingUseCase: IsPlayingUseCase,
    val seekToUseCase: SeekToUseCase,
    val stopUseCase: StopUseCase,
)