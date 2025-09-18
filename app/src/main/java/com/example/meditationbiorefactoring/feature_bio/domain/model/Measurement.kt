package com.example.meditationbiorefactoring.feature_bio.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Measurement(
    val timestamp: String,
    val bpm: Int,
    val brpm: Int,
    val siv: Int,
    val stress: String,
    @PrimaryKey val id: Int? = null
)
