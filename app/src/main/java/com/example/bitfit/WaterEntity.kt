package com.example.bitfit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
data class WaterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val waterAmount: Int,
    val date: String
)
