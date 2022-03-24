package ru.yundon.compositenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.yundon.compositenumber.Constants.EXCEPTION_MESSAGE_BINDING
import ru.yundon.compositenumber.R
import ru.yundon.compositenumber.databinding.FragmentGameFinishedBinding
import ru.yundon.compositenumber.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()


    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException(EXCEPTION_MESSAGE_BINDING)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupClickListener()
        bindViews(args.result)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun bindViews(gameResult: GameResult) = with(binding){

        if (gameResult.winner) emojiResult.setImageResource(R.drawable.ic_smile)
        else emojiResult.setImageResource(R.drawable.ic_sad)

        tvRequiredAnswers.text = String.format(
            getString(R.string.required_answer),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            gameResult.countOfRightAnswers
        )
        tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        tvScorePercentage.text = String.format(
            getString(R.string.score_percentage),
            getPercentOfRightAnswer()
        )

    }

    private fun retryGame(){
        findNavController().popBackStack()
    }

    private fun getPercentOfRightAnswer() = with(args.result){
        if (countOfQuestions == 0) 0
        else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()

    }

    private fun setupClickListener(){

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }
}