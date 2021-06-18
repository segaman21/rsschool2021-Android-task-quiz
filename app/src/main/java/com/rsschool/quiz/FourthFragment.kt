package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FourthFragment : Fragment(R.layout.fragment_quiz) {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val questionViewModel by viewModels<QuestionViewModel>()
    private var listener: OpenNextQuestion? = null
    private lateinit var selectOption:Question

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
        questionViewModel.questionListFour.observe(viewLifecycleOwner, {
            selectOption=it
            bind(it)
        })

        binding.previousButton.setOnClickListener { listener?.openThirdFragment() }
        binding.radioGroup.setOnCheckedChangeListener { _, checkId ->
            if (checkId != 1) {
                val answerView = view.findViewById<RadioButton>(checkId)
                val answer = answerView.text.toString()
                selectOption.choseAnswer = answer
                questionViewModel.setFourthAnswer(selectOption)
                binding.nextButton.setOnClickListener { listener?.openFiveFragment() }
            } else {
                Toast.makeText(context, "Выберите вариант", Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface OpenNextQuestion {
        fun openFiveFragment()
        fun openThirdFragment()
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