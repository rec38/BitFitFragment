package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {

    @Query("SELECT * FROM water_table ORDER BY id DESC")
    fun getAllWaterEntries(): Flow<List<WaterEntity>>

    @Insert
    suspend fun insertWaterEntry(waterEntity: WaterEntity)
}