package com.example.meditationbiorefactoring.feature_music.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.GetMeasurementByIdUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetCurrentPositionUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetDurationUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.GetTracksByTagUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.IsPlayingUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PauseUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.PlayUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.ResumeUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.SeekToUseCase
import com.example.meditationbiorefactoring.feature_music.domain.use_case.StopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
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
    private val isPlayingUseCase: IsPlayingUseCase,
    private val seekToUseCase: SeekToUseCase,
    private val stopUseCase: StopUseCase,
    private val getMeasurementByIdUseCase: GetMeasurementByIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(MusicState())
    val state: State<MusicState> = _state

    private val _progress = mutableFloatStateOf(0f)
    val progress: State<Float> = _progress

    private var progressJob: Job? = null
    private var loadTracksJob: Job? = null


    init {
        loadTracks("calm")
    }

    fun onEvent(event: MusicEvent) {
        when(event) {
            is MusicEvent.TrackClick -> {
                playUseCase(event.track.audioUrl)
                startObservingProgress()
                _state.value = _state.value.copy(
                    currentTrack = event.track,
                    isPlaying = true,
                    isEnd = false
                )
            }
            is MusicEvent.Pause -> {
                pauseUseCase()
                _state.value = _state.value.copy(isPlaying = isPlayingUseCase())
                stopObservingProgress()
            }
            is MusicEvent.Resume -> {
                resumeUseCase()
                _state.value = _state.value.copy(
                    isPlaying = isPlayingUseCase(),
                    isEnd = false
                )
                startObservingProgress()
            }
            is MusicEvent.SeekTo -> {
                seekToUseCase(event.positionMs)
            }
            is MusicEvent.TrackEnd -> {
                pauseUseCase()
                _state.value = _state.value.copy(isPlaying = isPlayingUseCase())
                seekToUseCase(0L)
                _state.value = _state.value.copy(
                    isEnd = true
                )
                stopObservingProgress()
            }
        }
    }

    private fun startObservingProgress() {
        progressJob?.cancel()
        progressJob = viewModelScope.launch {
            val duration = getDurationUseCase().toFloat()
            _state.value = _state.value.copy(
                duration = duration
            )
            while (isActive) {
                val position = getCurrentPositionUseCase().toFloat()

                val progress = if (duration > 0) {
                    position / duration
                } else 0f

                _progress.floatValue = progress

                if (position >= duration && duration > 0) {
                    onEvent(MusicEvent.TrackEnd)
                    break
                }

                Log.d("progress", "$position $duration")

                delay(100L)
            }
        }
    }

    private fun stopObservingProgress() {
        progressJob?.cancel()
        progressJob = null
    }

    fun loadByStressLevel(stressLevel: String?) {
        val tag = when (stressLevel) {
            "Low" -> "ambient+chillout"
            "Medium" -> "downtempo"
            "High" -> "calm+meditation"
            else -> "ambient+chillout+downtempo+calm+meditation"
        }
        loadTracks(tag)
    }

    fun loadFromMeasurement(id: Int) {
        viewModelScope.launch {
            val measurement = getMeasurementByIdUseCase(id)
            val tag = when (measurement?.stress) {
                "Low" -> "ambient+chillout"
                "Medium" -> "downtempo"
                "High" -> "calm+meditation"
                else -> "ambient+chillout+downtempo+calm+meditation"
            }
            loadTracks(tag)
        }
    }

    fun loadDefault() = loadTracks("ambient+chillout+downtempo+calm+meditation")

    private fun loadTracks(tag: String) {
        loadTracksJob?.cancel()
        loadTracksJob = getTracksByTagUseCase(tag)
            .onEach { tracks ->
                _state.value = _state.value.copy(
                    tracks = tracks,
                    isLoading = false,
                    error = if (tracks.isEmpty()) "No tracks found" else null
                )
            }
            .catch { e ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        stopObservingProgress()
        stopUseCase()
    }
}