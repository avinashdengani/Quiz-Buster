package com.example.quizbuster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import org.w3c.dom.Text

class quizActivity : AppCompatActivity() {

    lateinit var progressbarQuiz: ProgressBar
    lateinit var progressbarValue: TextView
    lateinit var tvQuestion: TextView
    lateinit var image: ImageView
    lateinit var tvOptionOne: TextView
    lateinit var tvOptionTwo: TextView
    lateinit var tvOptionThree: TextView
    lateinit var tvOptionFour: TextView
    lateinit var btnSubmitQuiz: Button
    lateinit var btnPreviousQuestion: Button
    lateinit var tvExplanationHeading: TextView
    lateinit var tvExplanation: TextView

    private var username:String? = null
    private val questionList = Constants.getQuestions()
    private var currentQuestion: Int = 1
    private val totalNumberOfQuestions: Int = questionList.size

    private var isOptionSelected: Boolean = false
    private var previousQuestion:Boolean = false
    private var numOfCorrectAnswers: Int = 0
    private var numOfSkippedAnswers: Int = 0
    private var numOfWrongAnswers: Int = 0
    /*private var isQuestionVisited: Boolean = false*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        username  = intent.getStringExtra(Constants.USERNAME)

        progressbarQuiz = findViewById<ProgressBar>(R.id.progressQuiz)
        progressbarValue = findViewById<TextView>(R.id.tvProgressValue)
        tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        tvOptionOne = findViewById<TextView>(R.id.tvOptionOne)
        tvOptionTwo = findViewById<TextView>(R.id.tvOptionTwo)
        tvOptionThree = findViewById<TextView>(R.id.tvOptionThree)
        tvOptionFour = findViewById<TextView>(R.id.tvOptionFour)
        image = findViewById<ImageView>(R.id.imgQuestionImage)
        btnSubmitQuiz = findViewById<Button>(R.id.btnSubmitQuiz)
        btnPreviousQuestion =  findViewById<Button>(R.id.btnPreviousQuestion)
        tvExplanation = findViewById<TextView>(R.id.tvExplanation)
        tvExplanationHeading = findViewById<TextView>(R.id.tvExplanationHeading)
    }
    public fun onOptionSelected(view: View){
        val question: Question = questionList.get(currentQuestion - 1)
        if(!previousQuestion){
            if(!isOptionSelected){
                val selectedOptionViewId = view.id
                val selectedOptionNumber = getSelectedOptionNumberFromViewId(selectedOptionViewId)
                val correctOptionNumber = questionList.get(currentQuestion - 1).correctOption

                question.option_choosed = selectedOptionNumber
                if(correctOptionNumber == selectedOptionNumber){

                    view.setBackgroundResource(R.drawable.correct_option_border)
                    (view as TextView).setTextColor(getColor(R.color.success_color))
                    numOfCorrectAnswers++
                }else{
                    view.setBackgroundResource(R.drawable.wrong_option_border)
                    (view as TextView).setTextColor(getColor(R.color.danger_color))
                    val correctOptionTextView = findViewById<TextView>(getViewIdFromOptionNumber(correctOptionNumber))
                    correctOptionTextView.setBackgroundResource(R.drawable.correct_option_border)
                    correctOptionTextView.setTextColor(getColor(R.color.success_color))
                    tvExplanationHeading.setVisibility(View.VISIBLE)
                    tvExplanation.setVisibility(View.VISIBLE)
                }
                isOptionSelected = true
            }
        }
    }
    public fun onSubmitQuiz(view: View){
        val question: Question = questionList.get(currentQuestion - 1)
        previousQuestion = false
        question.isAnswered = true
        if(!isOptionSelected){
            question.questionVisited = true
            numOfSkippedAnswers++
        }
        currentQuestion++

        if(currentQuestion == totalNumberOfQuestions){

            btnSubmitQuiz.text = "Submit Quiz"
            showNextQuestion()
        }else if(currentQuestion > totalNumberOfQuestions){
            numOfWrongAnswers = totalNumberOfQuestions - numOfCorrectAnswers - numOfSkippedAnswers
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(Constants.USERNAME, username)
            intent.putExtra(Constants.SKIPPED_ANSWERS, numOfSkippedAnswers)
            intent.putExtra(Constants.CORRECT_ANSWERS, numOfCorrectAnswers)
            intent.putExtra(Constants.TOTAL_NUMBER_OF_QUESTIONS, totalNumberOfQuestions)
            intent.putExtra(Constants.WRONG_ANSWERS, numOfWrongAnswers)
            startActivity(intent)
            finish()
        }else{
            showNextQuestion()
            btnPreviousQuestion.setVisibility(View.VISIBLE);
        }
    }
    public fun onPreviousQuestion(view: View){
        if(currentQuestion <= 2){
            btnPreviousQuestion.setVisibility(View.INVISIBLE)
        }
        showPreviousQuestion()
    }
    private fun showNextQuestion(){
        val question: Question = questionList.get(currentQuestion - 1)
        if(!question.questionVisited) {
            progressbarQuiz.progress = currentQuestion
            progressbarValue.text = "${question.id}/ ${totalNumberOfQuestions}"

            tvQuestion.text = question.body

            if (!question.hasImage) {
                image.setImageResource(question.image)
            } else {
                image.setImageResource(question.image)
            }

            tvOptionOne.text = question.options.get(0)
            tvOptionTwo.text = question.options.get(1)
            tvOptionThree.text = question.options.get(2)
            tvOptionFour.text = question.options.get(3)

            tvExplanation.text = question.explanation
            isOptionSelected = false
            setDefaultStatesOfOption()
            question.questionVisited = true
        }else{
            setDefaultStatesOfOptionForPreviousbtn(1)
        }
    }
    private fun showPreviousQuestion(){
        setDefaultStatesOfOption()
        previousQuestion = true
        isOptionSelected = true
        val question: Question = questionList.get(currentQuestion - 2)
        if(question.id != 10){
            btnSubmitQuiz.text = "Next Question"
        }
        setDefaultStatesOfOptionForPreviousbtn(2)
        currentQuestion = currentQuestion - 1
       }
    private fun getSelectedOptionNumberFromViewId(id:Int):Int{
        when(id){
            tvOptionOne.id -> return 1
            tvOptionTwo.id -> return 2
            tvOptionThree.id -> return 3
            tvOptionFour.id -> return 4
            else -> return 0
        }
    }

    private fun getViewIdFromOptionNumber(id:Int):Int{
        when(id){
            1 -> return tvOptionOne.id
            2 -> return tvOptionTwo.id
            3 -> return tvOptionThree.id
            4 -> return tvOptionFour.id
            else -> return 0
        }
    }
    private fun setDefaultStatesOfOptionForPreviousbtn(number:Int){
        val question: Question = questionList.get(currentQuestion - number)
        progressbarQuiz.progress = question.id
        progressbarValue.text = "${question.id}/${totalNumberOfQuestions}"
        tvQuestion.text = question.body
        setDefaultStatesOfOption()

        isOptionSelected = true
        progressbarQuiz.progress = question.id
        progressbarValue.text = "${question.id}/${totalNumberOfQuestions}"
        tvQuestion.text = question.body

        if (!question.hasImage) {
            image.setImageResource(question.image)
        } else {
            image.setImageResource(question.image)
        }

        tvOptionOne.text = question.options.get(0)
        tvOptionTwo.text = question.options.get(1)
        tvOptionThree.text = question.options.get(2)
        tvOptionFour.text = question.options.get(3)

        tvExplanation.text = question.explanation
        if(question.option_choosed == question.correctOption){
            val correctOptionTextView = findViewById<TextView>(getViewIdFromOptionNumber(question.option_choosed))
            correctOptionTextView.setBackgroundResource(R.drawable.correct_option_border)
            (correctOptionTextView as TextView).setTextColor(getColor(R.color.success_color))
        }else if(question.option_choosed == 0) {
            setDefaultStatesOfOption()
            if(question.isAnswered == false){
            isOptionSelected = false
            }
        }else{
            val wrongOptionTextView = findViewById<TextView>(getViewIdFromOptionNumber(question.option_choosed))
            wrongOptionTextView.setBackgroundResource(R.drawable.wrong_option_border)
            (wrongOptionTextView as TextView).setTextColor(getColor(R.color.danger_color))
            val correctOptionTextView = findViewById<TextView>(getViewIdFromOptionNumber(question.correctOption))
            correctOptionTextView.setBackgroundResource(R.drawable.correct_option_border)
            (correctOptionTextView as TextView).setTextColor(getColor(R.color.success_color))
            tvExplanationHeading.setVisibility(View.VISIBLE)
            tvExplanation.setVisibility(View.VISIBLE)
        }
    }
    private fun setDefaultStatesOfOption(){
        tvOptionOne.setBackgroundResource(R.drawable.default_border)
        tvOptionTwo.setBackgroundResource(R.drawable.default_border)
        tvOptionThree.setBackgroundResource(R.drawable.default_border)
        tvOptionFour.setBackgroundResource(R.drawable.default_border)

        tvOptionOne.setTextColor(getColor(R.color.primary_color))
        tvOptionTwo.setTextColor(getColor(R.color.primary_color))
        tvOptionThree.setTextColor(getColor(R.color.primary_color))
        tvOptionFour.setTextColor(getColor(R.color.primary_color))
        tvExplanationHeading.setVisibility(View.INVISIBLE)
        tvExplanation.setVisibility(View.INVISIBLE)
    }
}