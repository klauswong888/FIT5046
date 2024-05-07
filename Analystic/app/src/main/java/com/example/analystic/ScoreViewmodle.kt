package com.example.analystic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {
    private var scoreRepository: ScoreRepository = ScoreRepository(application)

    val dailyScores: LiveData<List<DailyScore>> = scoreRepository.getDailyScores().asLiveData()

    init {
        scoreRepository = ScoreRepository(application)
    }

    val allScores: LiveData<List<Score>> = scoreRepository.allScores.asLiveData()

    fun saveScore(score: Int, timeTaken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newScore = Score(
                0,
                uid = 0,
                name = "",
                quizID = 0,
                score = 0,
                timeTaken = ""
            )
            //insertScore(newScore)
        }

        fun insertScore(score: Score) = viewModelScope.launch(Dispatchers.IO) {
            try {
                scoreRepository.insert(score)
            } catch (e: Exception) {
                // Handle insertion error (e.g., logging, UI notification)
            }
        }

        fun updateScore(score: Score) = viewModelScope.launch(Dispatchers.IO) {
            try {
                scoreRepository.update(score)
            } catch (e: Exception) {
                // Handle update error
            }
        }

        fun deleteScore(score: Score) = viewModelScope.launch(Dispatchers.IO) {
            try {
                scoreRepository.delete(score)
            } catch (e: Exception) {
                // Handle deletion error
            }
        }

        suspend fun getScoresByUserId(id:Int): Score? =
        scoreRepository.getScoresById(id)

        suspend fun getScoresByQuizId(quizId: Int): List<Score> =
            scoreRepository.getScoresByQuizId(quizId)
    }}