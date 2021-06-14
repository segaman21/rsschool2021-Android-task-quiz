package com.rsschool.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsschool.quiz.data.QuestinDesc
import com.rsschool.quiz.data.Question

class QuestionViewModel : ViewModel() {
    var questionListOne: MutableLiveData<Question> = MutableLiveData()
    var questionListTwo: MutableLiveData<Question> = MutableLiveData()
    var questionListThree: MutableLiveData<Question> = MutableLiveData()
    var questionListFour: MutableLiveData<Question> = MutableLiveData()
    var questionListFive: MutableLiveData<Question> = MutableLiveData()
    init {
        questionListOne.value = QuestinDesc.questionOne()
    }
    init {
        questionListTwo.value = QuestinDesc.questionTwo()
    }
    init {
        questionListThree.value = QuestinDesc.questionTwo()
    }
    init {
        questionListFour.value = QuestinDesc.questionTwo()
    }
    init {
        questionListFive.value = QuestinDesc.questionTwo()
    }

}