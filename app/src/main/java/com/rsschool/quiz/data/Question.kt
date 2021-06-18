package com.rsschool.quiz.data

data class Question(
    val question: String = "",
    val optionOne: String = "",
    val optionTwo: String = "",
    val optionThree: String = "",
    val optionFour: String = "",
    val optionFive: String = "",
    val rightAnswer: String= "",
    var choseAnswer: String= ""
)