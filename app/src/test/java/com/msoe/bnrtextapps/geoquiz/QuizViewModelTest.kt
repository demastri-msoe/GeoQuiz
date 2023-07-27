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
    @Test
    fun wrapsAroundQuestionBank() {
        // uses the specific value specified for currentIndex
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)

        // assumes knowledge about number, order and contests of questionBank :(
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }
    @Test
    fun dynamicWrapsAroundQuestionBank() {
        // we can remove at least the reliance on knowing the size of the question bank
        var quizViewModel = QuizViewModel(SavedStateHandle()) // start with default index
        val nbrQuestions = quizViewModel.nbrQuestions

        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to nbrQuestions-1))
        quizViewModel = QuizViewModel(savedStateHandle)

        // still assumes knowledge about order and contests of questionBank :(
        // how would you fix this??
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun firstAnswer() {
        val quizViewModel = QuizViewModel(SavedStateHandle())
        assertTrue(quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun firstFalseAnswer() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        quizViewModel.moveToNext()
        quizViewModel.moveToNext()
        assertFalse(quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun testAllAnswers() {
        val answers: BooleanArray = booleanArrayOf(true, true, false, false, true, true)

        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        for( ans in answers ) {
            if( ans )
                assertTrue(quizViewModel.currentQuestionAnswer)
            else
                assertFalse(quizViewModel.currentQuestionAnswer)
            // could just assertEquals( ans, curAns ), but challenge wanted to test assertTrue/False
            quizViewModel.moveToNext()
        }
    }
}