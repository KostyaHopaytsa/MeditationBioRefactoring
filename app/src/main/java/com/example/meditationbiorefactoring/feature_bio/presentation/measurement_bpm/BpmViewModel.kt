package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.model.BpmResult
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.BpmMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import kotlinx.coroutines.launch

@HiltViewModel
class BpmViewModel @Inject constructor(
    private val bpmMeasurementUseCase: BpmMeasurementUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BpmState())
    val state: State<BpmState> = _state

    val progress = mutableStateOf(0f)

    fun onEvent(event: BpmEvent) {
        when (event) {
            is BpmEvent.Start -> {
                _state.value = BpmState(isLoading = false, isMeasuring = true)
            }
            is BpmEvent.Retry -> {
                _state.value = BpmState()
                _state.value = BpmState(isLoading = false, isMeasuring = true)
            }
            is BpmEvent.Reset -> {
                _state.value = BpmState()
                _state.value = BpmState(isLoading = false, isMeasuring = true)
            }
            is BpmEvent.Error -> {
                _state.value = _state.value.copy(
                    isMeasuring = false,
                    error = event.error
                )
            }
            is BpmEvent.FrameCaptured -> {
                processFrame(event.buffer)
            }
        }
    }

    private fun processFrame(buffer: ByteArray) {
        viewModelScope.launch {
            when (val result = bpmMeasurementUseCase(buffer)) {
                is BpmResult.Success -> {
                    _state.value = _state.value.copy(
                        isMeasuring = false,
                        isMeasured = true,
                        value = result.bpm.toString(),
                        status = if (result.bpm < 60) "low" else if (result.bpm > 100) "high" else "normal"
                    )
                }
                is BpmResult.Invalid -> {
                    _state.value = _state.value.copy(
                        isMeasuring = false,
                        error = ErrorType.MeasureError
                    )
                }
                is BpmResult.Error -> {
                    _state.value = _state.value.copy(
                        error = ErrorType.UnknownError
                    )
                }
            }
        }
    }
}