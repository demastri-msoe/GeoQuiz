package com.msoe.bnrtextapps.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.util.Log
import com.msoe.bnrtextapps.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivityLogEntries"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    val answers = IntArray(questionBank.size) { -1 }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        updateQuestion()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        binding.trueButton.isEnabled = (answers[currentIndex] == -1)
        binding.falseButton.isEnabled = (answers[currentIndex] == -1)

    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            answers[currentIndex] = 1
            R.string.correct_toast
        } else {
            answers[currentIndex] = 0
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        if( allAnswered() )
            showPctMessage()
    }
    fun allAnswered(): Boolean {
        return answers.indexOf(-1) == -1
    }

    fun showPctMessage() {
        val pct = answers.sum()*100.0 / questionBank.size
        val outMsg = "You answered $pct% of questions correctly"

        Toast.makeText(
            this,
            outMsg,
            Toast.LENGTH_SHORT
        ).show()
    }

}