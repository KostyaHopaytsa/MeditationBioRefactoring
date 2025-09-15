package com.example.meditationbiorefactoring.feature_music.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(MusicState())
    val state: State<MusicState> = _state
}