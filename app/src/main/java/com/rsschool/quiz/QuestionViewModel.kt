package com.rsschool.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsschool.quiz.data.QuestionDesc
import com.rsschool.quiz.data.Question

class QuestionViewModel : ViewModel() {
    var questionListOne = MutableLiveData<Question>()
    var questionListTwo = MutableLiveData<Question>()
    var questionListThree = MutableLiveData<Question>()
    var questionListFour = MutableLiveData<Question>()
    var questionListFive = MutableLiveData<Question>()

    init {
        questionListOne.value = QuestionDesc.questionOne()
        questionListTwo.value = QuestionDesc.questionTwo()
        questionListThree.value = QuestionDesc.questionThree()
        questionListFour.value = QuestionDesc.questionFour()
        questionListFive.value = QuestionDesc.questionFive()
    }

    fun setFirstAnswer(answer:Question){
        questionListOne.value=answer
    }
    fun setSecondAnswer(answer: Question){
        questionListTwo.value=answer
    }
    fun setThirdAnswer(answer: Question){
        questionListThree.value=answer
    }
    fun setFourthAnswer(answer: Question){
        questionListFour.value=answer
    }
    fun setFifthAnswer(answer: Question){
        questionListFive.value=answer
    }


}