package com.example.meditationbiorefactoring.feature_music.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetTracksByTagUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PauseUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PlayUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.ResumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTracksByTagUseCase: GetTracksByTagUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val resumeUseCase: ResumeUseCase,
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
                _state.value = _state.value.copy(isPlaying = true)
                playUseCase(event.track.audioUrl)
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
}