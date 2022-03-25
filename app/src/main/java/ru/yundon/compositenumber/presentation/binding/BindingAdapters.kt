package ru.yundon.compositenumber.presentation.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.yundon.compositenumber.R
import ru.yundon.compositenumber.domain.entity.GameResult



@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {

    textView.text = String.format(textView.context.getString(R.string.required_answer), count)
}

@BindingAdapter("scoreAnswer")
fun bindScoreAnswer(textView: TextView, score: Int) {

    textView.text = String.format(textView.context.getString(R.string.score_answers), score)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {

    textView.text = String.format(textView.context.getString(R.string.required_percentage), count)
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage), getPercentOfRightAnswer(gameResult)
    )
}

private fun getPercentOfRightAnswer(gameResult: GameResult) = with(gameResult) {

    if (countOfQuestions == 0) 0
    else ((countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()

}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {

    if (winner) imageView.setImageResource(R.drawable.ic_smile)
    else imageView.setImageResource(R.drawable.ic_sad)
}