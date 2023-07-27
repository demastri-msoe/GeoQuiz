package com.msoe.bnrtextapps.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.lang.Exception
private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val hasCheated = BooleanArray(questionBank.size) { false }
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var isCheater: Boolean
        get() =
            hasCheated[currentIndex]
        set(value) {
            value.also { hasCheated[currentIndex] = it }
        }

    val nbrCheats: Int
        get() =
            hasCheated.count { it }
    val exceededCheatLimit: Boolean
        get() = nbrCheats >= 3

    val cheatsRemaining: Int
        get() = 3-nbrCheats
    fun moveToNext() {
        Log.d(TAG, "Updating question text")
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}