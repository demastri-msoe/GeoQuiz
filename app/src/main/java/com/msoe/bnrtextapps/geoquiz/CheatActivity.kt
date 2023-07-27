package com.msoe.bnrtextapps.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.msoe.bnrtextapps.geoquiz.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.msoe.textapps.ch7.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.msoe.textapps.ch7.geoquiz.answer_shown"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private val useCompanionMethod = true
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}