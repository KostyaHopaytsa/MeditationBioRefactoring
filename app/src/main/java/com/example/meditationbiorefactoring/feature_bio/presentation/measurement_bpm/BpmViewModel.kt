package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_bpm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.BpmMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.ResetBpmMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import kotlinx.coroutines.launch

@HiltViewModel
class BpmViewModel @Inject constructor(
    private val bpmMeasurementUseCase: BpmMeasurementUseCase,
    private val resetBpmMeasurementUseCase: ResetBpmMeasurementUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BpmState())
    val state: State<BpmState> = _state

    private val _progress = mutableFloatStateOf(0f)
    val progress: State<Float> = _progress
    private var firstProgressValue: Float? = null

    fun onEvent(event: BpmEvent) {
        when (event) {
            is BpmEvent.Start -> {
                _state.value = _state.value.copy(
                    isMeasuring = true,
                    isLoading = false,
                    isTorchEnabled = true,
                )
            }
            is BpmEvent.Retry -> {
                viewModelScope.launch {
                    resetBpmMeasurementUseCase()
                    _state.value = BpmState()
                    _state.value = _state.value.copy(
                        isMeasuring = true,
                        isTorchEnabled = true,
                    )
                }
            }
            is BpmEvent.Error -> {
                _state.value = _state.value.copy(
                    isMeasuring = false,
                    error = event.error,
                )
            }
            is BpmEvent.FrameCaptured -> {
                processFrame(event.buffer)
            }
        }
    }

    private fun processFrame(buffer: ByteArray) {
        viewModelScope.launch {
            val analysis = bpmMeasurementUseCase(buffer)
            _progress.floatValue = analysis.progress

            if (firstProgressValue == null) {
                firstProgressValue = _progress.floatValue
            }

            Log.d("firstProgressValue", "$firstProgressValue")

            if(_progress.floatValue == 1F - firstProgressValue!! * 2) {
                _state.value = _state.value.copy(
                    isTorchEnabled = false,
                )
            }

            when (val result = analysis.result) {
                is MeasurementResult.Success -> {
                    _state.value = _state.value.copy(
                        isMeasuring = false,
                        isMeasured = true,
                        value = result.value.toString(),
                        status = if (result.value < 60) "low" else if (result.value > 100) "high" else "normal",
                    )
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