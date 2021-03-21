package com.example.quizbuster

class Question(
        val id: Int,
        val body: String,
        val hasImage: Boolean,
        val image: Int,
        val options: ArrayList<String>,
        val correctOption: Int
) {
}