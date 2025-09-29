package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.GetMeasurementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BioHistoryViewModel @Inject constructor(
    private val getMeasurementsUseCase: GetMeasurementsUseCase
): ViewModel() {

    private val _state = mutableStateOf(BioHistoryState())
    val state:State<BioHistoryState> = _state

    init {
        getMeasurementsUseCase()
            .onEach { measurements ->
                _state.value = _state.value.copy(
                    measurements = measurements
                )
            }
            .launchIn(viewModelScope)
    }
}