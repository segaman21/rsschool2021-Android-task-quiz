package com.rsschool.quiz


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rsschool.quiz.data.Question
import com.rsschool.quiz.databinding.ResoultFragmentBinding
import kotlin.system.exitProcess

class ResultFragment : Fragment(R.layout.resoult_fragment) {
    private var _binding: ResoultFragmentBinding? = null
    private val binding get() = _binding!!
    private var count= 0
    private val questionViewModel by viewModels<QuestionViewModel>()



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
//        checkResult(questionViewModel.questionListOne)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun share() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "result")
        }
        startActivity(sendIntent)
    }

//    private fun checkResult(question: Question){
//        if (question.rightAnswer==question.choseAnswer)
//            count+=1
//    }



}