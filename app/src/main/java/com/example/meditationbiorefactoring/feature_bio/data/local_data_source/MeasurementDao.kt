package com.example.meditationbiorefactoring.feature_bio.data.local_data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meditationbiorefactoring.feature_bio.domain.model.Measurement
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {

    @Query("SELECT * FROM measurement")
    fun getMeasurements(): Flow<List<Measurement>>

    @Query("SELECT * FROM measurement WHERE id == :id")
    suspend fun getMeasurementById(id: Int): Measurement?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurement(measurement: Measurement)

    @Delete
    suspend fun deleteMeasurement(measurement: Measurement)
}