package com.example.meditationbiorefactoring.feature_bio.domain.model

sealed class BpmResult {
    data class Success(val bpm: Int) : BpmResult()
    data object Invalid : BpmResult()
    data object Error : BpmResult()
}