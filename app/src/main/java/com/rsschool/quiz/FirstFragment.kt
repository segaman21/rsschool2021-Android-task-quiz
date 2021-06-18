package com.rsschool.quizn

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.addCallback
import com.rsschool.quiz.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.QuestionViewModel
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FirstFragment : Fragment(R.layout.fragment_quiz) {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val questionViewModel by viewModels<QuestionViewModel>()
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

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.previousButton.setBackgroundColor(R.color.gray)
        questionViewModel.questionListOne.observe(viewLifecycleOwner, {
            selectOption = it
            bind(it)
        })
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                val answerView = view.findViewById<RadioButton>(checkedId)
                val answer = answerView.text.toString()
                selectOption.choseAnswer = answer
                questionViewModel.setFirstAnswer(selectOption)
                binding.nextButton.setOnClickListener { listener?.openSecondFragment() }
            } else Toast.makeText(context, "Выберите вариант", Toast.LENGTH_SHORT).show()
        }
        requireActivity().onBackPressedDispatcher.addCallback(){}
    }

    interface OpenNextQuestion {
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