package com.example.meditationbiorefactoring.feature_bio.presentation.measurement.measurement_siv

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.domain.model.MeasurementResult
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.ResetSivMeasurementUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.StartSivRecordingUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.use_case.StopSivAndAnalyzeUseCase
import com.example.meditationbiorefactoring.feature_bio.domain.util.BioParamType
import com.example.meditationbiorefactoring.feature_bio.presentation.measurement.MeasurementAggregator
import com.example.meditationbiorefactoring.feature_bio.presentation.util.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SivViewModel @Inject constructor(
    private val startSivRecordingUseCase: StartSivRecordingUseCase,
    private val stopSivAndAnalyzeUseCase: StopSivAndAnalyzeUseCase,
    private val resetSivMeasurementUseCase: ResetSivMeasurementUseCase,
    private val aggregator: MeasurementAggregator,
): ViewModel() {

    private val _state = mutableStateOf(SivState())
    val state: State<SivState> = _state

    private val _navigateEvent = Channel<String>(Channel.BUFFERED)
    val navigateEvent = _navigateEvent.receiveAsFlow()

    fun onEvent(event: SivEvent) {
        when (event) {
            is SivEvent.Start -> {
                viewModelScope.launch {
                    startSivRecordingUseCase()
                    _state.value = _state.value.copy(isMeasuring = true)
                }
            }
            is SivEvent.Stop -> {
                viewModelScope.launch {
                    val analysis = stopSivAndAnalyzeUseCase()
                    when (val result = analysis.result) {
                        is MeasurementResult.Success -> {
                            _state.value = _state.value.copy(
                                isMeasuring = false,
                                isMeasured = true,
                                value = result.value.toString(),
                                status = if (result.value < 0.03) "low"
                                else if (result.value > 0.09) "high"
                                else "normal",
                            )
                            aggregator.updateMeasurement(BioParamType.siv, result.value)
                            aggregator.computeOverallStress()
                        }
                        is MeasurementResult.Invalid -> {
                            _state.value = _state.value.copy(
                                error = ErrorType.MeasureError
                            )
                        }
                        is MeasurementResult.Error -> {
                            _state.value = _state.value.copy(
                                error = ErrorType.UnknownError
                            )
                        }
                    }
                }
            }
            is SivEvent.Retry -> {
                viewModelScope.launch {
                    resetSivMeasurementUseCase()
                    _state.value = SivState()
                }
            }
            is SivEvent.NavigateClick -> {
                viewModelScope.launch {
                    aggregator.saveMeasurement()
                    _navigateEvent.send(aggregator.state.value.overallStress)
                }
            }
        }
    }
}