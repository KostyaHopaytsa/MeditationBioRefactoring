package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditationbiorefactoring.feature_bio.presentation.common.MeasurementState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BpmViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(MeasurementState())
    val state: State<MeasurementState> = _state


}