package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BioHistoryViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(BioHistoryState())
    val state:State<BioHistoryState> = _state
}