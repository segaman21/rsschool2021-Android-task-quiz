package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quizn.FirstFragment

class MainActivity : AppCompatActivity(),FirstFragment.OpenNextQuestion,SecondFragment.OpenPrevQuestion {
    private val quiz=FirstFragment()
    private val quizTwo=SecondFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,quiz)
        transaction.commit()
    }

    override fun OpenSecondQuestion() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView,quizTwo)
                commit()
            }
    }

    override fun OpenFirstQuestion() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView,quiz)
                commit()
            }
    }


}