package com.example.analystic

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class ScoreRepository (application: Application) {
    private var scoreDAO: ScoreDAO = ScoreDatabase.getDatabase(application).scoreDAO()
    val allScores: Flow<List<Score>> = scoreDAO.getAllScores()
    fun getDailyScores(): Flow<List<DailyScore>> = scoreDAO.getDailyScores()

    suspend fun insert(score: Score){
        scoreDAO.insertScore(score)
    }

    suspend fun update(score: Score){
        scoreDAO.updateScore(score)
    }

    suspend fun delete(score: Score){
        scoreDAO.deleteScore(score)
    }

    suspend fun getScoresById(id: Int): Score? =
        scoreDAO.getScoreById(id)


    suspend fun deleteScoresById(id : Int){
        scoreDAO.deleteScoreById(id) }

    suspend fun getScoresByQuizId(quizId: Int): List<Score> =
        scoreDAO.getScoresByQuizId(quizId)
}