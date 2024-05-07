package com.example.analystic

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyScore(
    @PrimaryKey(autoGenerate = true)
    val day: String,
    val totalScore: Int,
    val avgTime: Float
)
