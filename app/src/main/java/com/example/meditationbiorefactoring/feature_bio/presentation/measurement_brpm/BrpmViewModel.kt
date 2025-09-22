package com.example.meditationbiorefactoring.feature_bio.presentation.measurement_brpm

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationbiorefactoring.feature_bio.presentation.common.ErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class BrpmViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(BrpmState())
    val state: State<BrpmState> = _state

    fun onEvent(event: BrpmEvent) {
        when(event) {
            is BrpmEvent.Start -> {
                startMeasurement()
            }
            is BrpmEvent.Retry -> {
                _state.value = BrpmState()
                startMeasurement()
            }
            is BrpmEvent.Reset -> {
                _state.value = BrpmState()
                startMeasurement()
            }
            is BrpmEvent.Error -> {
                setError(event.error)
            }
        }
    }

    private fun startMeasurement() {
        _state.value =  _state.value.copy(isLoading = true)

        viewModelScope.launch {
            try {

                delay(1000)

                if(Random.nextBoolean()) {
                    throw RuntimeException("Accelerator failed")
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    isMeasuring = true,
                )

                delay(2000)

                if(Random.nextBoolean()) {
                    throw RuntimeException("Measurement failed")
                }

                _state.value = _state.value.copy(
                    isMeasuring = false,
                    isMeasured= true,
                    value = "10",
                    status = "low",
                )
            } catch (e: Exception) {
                val errorType = when (e.message) {
                    "Accelerator failed" -> ErrorType.SensorError
                    "Measurement failed" -> ErrorType.MeasureError
                    else -> ErrorType.UnknownError
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    isMeasuring = false,
                    error = errorType
                )
            }
        }
    }

    private fun setError(error: ErrorType) {
        _state.value = _state.value.copy(
            isLoading = false,
            isMeasuring = false,
            error = error
        )
    }
}