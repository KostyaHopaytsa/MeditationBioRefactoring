package com.example.meditationbiorefactoring.feature_music.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetCurrentPositionUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetDurationUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetTracksByTagUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.IsPlayingUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PauseUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PlayUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.ResumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTracksByTagUseCase: GetTracksByTagUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val resumeUseCase: ResumeUseCase,
    private val getCurrentPositionUseCase: GetCurrentPositionUseCase,
    private val getDurationUseCase: GetDurationUseCase,
    private val isPlayingUseCase: IsPlayingUseCase
): ViewModel() {

    private val _state = mutableStateOf(MusicState())
    val state: State<MusicState> = _state

    init {
        try {
            getTracksByTagUseCase("calm")
                .onEach { tracks ->
                    _state.value = _state.value.copy(
                        tracks = tracks
                    )
                }
                .launchIn(viewModelScope)
        } catch (e: Exception) {
            Log.e("MusicViewModel", "Error fetching tracks", e)
        }
    }

    fun onEvent(event: MusicEvent) {
        when(event) {
            is MusicEvent.OnTrackClick -> {
                _state.value = _state.value.copy(currentTrack = event.track)
                onEvent(MusicEvent.Play(event.track))
            }
            is MusicEvent.Play -> {
                playUseCase(event.track.audioUrl)
                observeProgress()
                _state.value = _state.value.copy(isPlaying = true)
            }
            is MusicEvent.Pause -> {
                pauseUseCase()
                _state.value = _state.value.copy(isPlaying = false)
            }
            is MusicEvent.Resume -> {
                resumeUseCase()
                _state.value = _state.value.copy(isPlaying = true)
            }
            is MusicEvent.SeekTo -> {
                TODO()
            }
        }
    }

    private fun observeProgress() {
        viewModelScope.launch {
            while (true) {
                val position = getCurrentPositionUseCase()
                val duration = getDurationUseCase()

                val progress = if (duration > 0) {
                    position.toFloat() / duration
                } else 0f

                _state.value = _state.value.copy(
                    progress = progress,
                )

                delay(100L)
            }
        }
    }
}