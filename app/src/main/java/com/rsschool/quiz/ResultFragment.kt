package com.rsschool.quiz


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.ResoultFragmentBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment(R.layout.resoult_fragment) {
    private var _binding: ResoultFragmentBinding? = null
    private val binding get() = _binding!!
    private var count = 0
    private val questionViewModel by activityViewModels<QuestionViewModel>()
    private var res: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResoultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.share.setOnClickListener { share() }
        binding.close.setOnClickListener { exitProcess(0) }
        binding.again.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        checkResult(questionViewModel.questionListOne.value)
        checkResult(questionViewModel.questionListTwo.value)
        checkResult(questionViewModel.questionListThree.value)
        checkResult(questionViewModel.questionListFour.value)
        checkResult(questionViewModel.questionListFive.value)
        res = count.toDouble() / 5 * 100
        binding.result.text = "Your result: ${res.toInt()}% "
        requireActivity().onBackPressedDispatcher.addCallback() { }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun share() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT, "Ваш результат: ${res.toInt()}% \n\n " +
                        "1) ${questionViewModel.questionListOne.value?.let { shareResult(it) }}\n" +
                        "2) ${questionViewModel.questionListTwo.value?.let { shareResult(it) }}\n" +
                        "3) ${questionViewModel.questionListThree.value?.let { shareResult(it) }}\n" +
                        "4) ${questionViewModel.questionListFour.value?.let { shareResult(it) }}\n" +
                        "5) ${questionViewModel.questionListFive.value?.let { shareResult(it) }}\n"
            )
        }
        startActivity(sendIntent)
    }

    private fun checkResult(question: Question?): Int {
        if (question != null) {
            if (question.rightAnswer == question.choseAnswer)
                count += 1
        }
        return count
    }

    private fun shareResult(question: Question): String {
        return "${question.question} \n" +
                "Ваш ответ: ${question.choseAnswer}\n"
    }
}