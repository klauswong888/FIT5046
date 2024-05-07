package com.example.analystic

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uid: Int, // User ID
    val name: String, //User Name
    val score: Int,
    val quizID: Int, // Quiz ID
    val timeTaken: String? = null,
    val date: Long? = null,
)

