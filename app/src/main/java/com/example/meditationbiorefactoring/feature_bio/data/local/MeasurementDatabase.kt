package com.example.meditationbiorefactoring.feature_bio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement

@Database(
    entities = [Measurement::class],
    version = 1
)
abstract class MeasurementDatabase : RoomDatabase() {

    abstract val measurementDao: MeasurementDao

    companion object {
        const val DATABASE_NAME = "measurement_db"
    }
}