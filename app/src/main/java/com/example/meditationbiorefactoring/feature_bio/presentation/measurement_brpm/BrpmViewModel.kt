package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.util.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.BrpmMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.ResetBrpmMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.SetBrpmUseCase
import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrpmViewModel @Inject constructor(
    private val brpmMeasurementUseCase: BrpmMeasurementUseCase,
    private val resetBrpmMeasurementUseCase: ResetBrpmMeasurementUseCase,
    private val setBrpmUseCase: SetBrpmUseCase
): ViewModel() {

    private val _state = mutableStateOf(BrpmState())
    val state: State<BrpmState> = _state
    private val _progress = mutableFloatStateOf(0f)
    val progress: State<Float> = _progress

    fun onEvent(event: BrpmEvent) {
        when(event) {
            is BrpmEvent.Start -> {
                _state.value = _state.value.copy(
                    isMeasuring = true,
                    isLoading = false,
                )
            }
            is BrpmEvent.Retry -> {
                viewModelScope.launch {
                    resetBrpmMeasurementUseCase()
                    _state.value = BrpmState()
                    _state.value = _state.value.copy(
                        isMeasuring = true
                    )
                }
            }

            is BrpmEvent.Error -> {
                _state.value = _state.value.copy(
                    isMeasuring = false,
                    error = event.error,
                )
            }
            is BrpmEvent.DataCaptured -> {
                processFrame(event.z)
            }
        }
    }

    private fun processFrame(z: Float) {
        viewModelScope.launch {
            val analysis = brpmMeasurementUseCase(z)
            _progress.floatValue = analysis.progress

            when (val result = analysis.result) {
                is MeasurementResult.Success -> {
                    _state.value = _state.value.copy(
                        isMeasuring = false,
                        isMeasured = true,
                        value = result.value.toString(),
                        status = if (result.value < 12) "low"
                        else if (result.value > 25) "high"
                        else "normal",
                    )
                    setBrpmUseCase(result.value)
                }
                is MeasurementResult.Invalid -> {
                    _state.value = _state.value.copy(
                        isMeasuring = false,
                        error = ErrorType.MeasureError,
                    )
                }
                is MeasurementResult.Error -> {
                    _state.value = _state.value.copy(
                        error = ErrorType.UnknownError,
                    )
                }
            }
        }
    }
}