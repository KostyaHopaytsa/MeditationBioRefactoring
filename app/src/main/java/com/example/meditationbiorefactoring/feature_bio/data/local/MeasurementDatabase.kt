package com.example.meditationbiorefactoring.feature_bio.data.local

import androidx.room.Database
import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement

@Database(
    entities = [Measurement::class],
    version = 1
)
abstract class MeasurementDatabase {

    abstract val measurementDao: MeasurementDao

    companion object {
        const val DATABASE_NAME = "measurement_db"
    }
}