package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

data class BioHistoryState(
    //val measurements: List<BioMeasurement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
