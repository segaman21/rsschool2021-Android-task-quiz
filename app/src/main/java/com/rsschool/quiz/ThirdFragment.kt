package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding

class ThirdFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val questionViewModel by activityViewModels<QuestionViewModel>()
    private var listener: OpenNextQuestion? = null
    private lateinit var selectOption: Question

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OpenNextQuestion)
            listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.isEnabled = false
        questionViewModel.questionListThree.observe(viewLifecycleOwner, {
            selectOption = it
            bind(it)
        })
        binding.toolbar.title = "Question 3"
        binding.toolbar.setNavigationOnClickListener { listener?.openSecondFragment() }
        binding.previousButton.setOnClickListener { listener?.openSecondFragment() }
        binding.radioGroup.setOnCheckedChangeListener { _, checkId ->
            if (checkId != 1) {
                binding.nextButton.isEnabled = true
                val answerView = view.findViewById<RadioButton>(checkId)
                val answer = answerView.text.toString()
                if (answer != "") {
                    selectOption.choseAnswer = answer
                    questionViewModel.setThirdAnswer(selectOption)
                } else {
                    questionViewModel.setThirdAnswer(selectOption)
                }
                binding.nextButton.setOnClickListener { listener?.openFourthFragment() }
            }
        }
    }

    interface OpenNextQuestion {
        fun openFourthFragment()
        fun openSecondFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun bind(quest: Question) {
        binding.question.text = quest.question
        binding.optionOne.text = quest.optionOne
        binding.optionTwo.text = quest.optionTwo
        binding.optionThree.text = quest.optionThree
        binding.optionFour.text = quest.optionFour
        binding.optionFive.text = quest.optionFive
    }
}