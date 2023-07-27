package com.msoe.bnrtextapps.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test

class QuizViewModelTest {
    // setup - given
    // testing - when
    // verification - then

    @Test
    fun providesExpectedQuestionText() {
        // uses the default values from the app for currentIndex
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }
}