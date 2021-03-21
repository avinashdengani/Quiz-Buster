package com.example.quizbuster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var tvSkippedAnswers: TextView
    lateinit var tvCorrectAnswers: TextView
    lateinit var tvTotalQuestions: TextView
    lateinit var tvWrongAnswers: TextView
    lateinit var tvUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USERNAME)
        val numOfSkippedAnswers = intent.getIntExtra(Constants.SKIPPED_ANSWERS, 0)
        val numOfCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val totalNumberOfQuestions = intent.getIntExtra(Constants.TOTAL_NUMBER_OF_QUESTIONS, 0)
        val numOfWrongAnwers = intent.getIntExtra(Constants.WRONG_ANSWERS, 0)
        tvCorrectAnswers = findViewById<TextView>(R.id.tvCorrectAnswers)
        tvSkippedAnswers = findViewById<TextView>(R.id.tvSkippedAnswers)
        tvTotalQuestions = findViewById<TextView>(R.id.tvTotalQuestion)
        tvWrongAnswers = findViewById<TextView>(R.id.tvWrongAnswers)
        tvUsername = findViewById<TextView>(R.id.tvUsername)

        tvCorrectAnswers.text = "${numOfCorrectAnswers}"
        tvSkippedAnswers.text = "${numOfSkippedAnswers}"
        tvTotalQuestions.text = "${totalNumberOfQuestions}"
        tvWrongAnswers.text = "${numOfWrongAnwers}"
        tvUsername.text = "${username}"
    }

    public fun onFinish(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}