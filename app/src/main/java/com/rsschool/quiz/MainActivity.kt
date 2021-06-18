package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quizn.FirstFragment

class MainActivity : AppCompatActivity(), FirstFragment.OpenNextQuestion,
    SecondFragment.OpenPrevQuestion, FourthFragment.OpenNextQuestion,
    ThirdFragment.OpenNextQuestion, FifthFragment.OpenNextQuestion {
    private val quiz = FirstFragment()
    private val quizTwo = SecondFragment()
    private val quizThree = ThirdFragment()
    private val quizFour = FourthFragment()
    private val quizFive = FifthFragment()
    private val result = ResultFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, quiz)
        transaction.commit()
    }

    override fun openFirstFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, quiz)
                addToBackStack(null)
                commit()
            }
    }

    override fun openSecondFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, quizTwo)
                addToBackStack(null)
                commit()
            }
    }

    override fun openThirdFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, quizThree)
                addToBackStack(null)
                commit()
            }
    }

    override fun openFourthFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, quizFour)
                addToBackStack(null)
                commit()
            }
    }

    override fun openResult() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, result)
                addToBackStack(null)
                commit()
            }
    }

    override fun openFiveFragment() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainerView, quizFive)
                addToBackStack(null)
                commit()
            }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount in 1..4) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}