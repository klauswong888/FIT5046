package com.example.analystic

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDAO {

    // Get all scores (Flow for potential UI updates)
    @Query("SELECT * FROM Score")
    fun getAllScores(): Flow<List<Score>>

    // Get score by ID (suspend for single-time operations)
    @Query("SELECT * FROM Score WHERE id = :id")
    suspend fun getScoreById(id: Int): Score?

    @Query("SELECT * FROM Score WHERE quizId = :quizId")
    suspend fun getScoresByQuizId(quizId: Int): List<Score>

    // Insert score (replace existing with same ID)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)

    @Query("SELECT strftime('%Y-%m-%d', date / 1000, 'unixepoch') AS day, SUM(score) AS totalScore, AVG(CAST(timeTaken AS FLOAT)) AS avgTime FROM Score GROUP BY day")
    fun getDailyScores(): Flow<List<DailyScore>>

    // Update score (modify existing entry)
    @Update
    suspend fun updateScore(score: Score)

    // Delete score (remove from table)
    @Delete
    suspend fun deleteScore(score: Score)

    @Delete
    suspend fun deleteScoreById(id: Int)

}