package com.example.quizbuster

class Question(
        val id: Int,
        val body: String,
        val hasImage: Boolean,
        val image: Int,
        val options: ArrayList<String>,
        val correctOption: Int,
        val explanation: String,
        var questionVisited:Boolean,
        var option_choosed: Int,
        var isAnswered: Boolean
) {
}