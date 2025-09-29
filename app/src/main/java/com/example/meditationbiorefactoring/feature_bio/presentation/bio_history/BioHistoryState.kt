package com.example.meditationbiorefactoring.feature_bio.presentation.bio_history

import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement

data class BioHistoryState(
    val measurements: List<Measurement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
