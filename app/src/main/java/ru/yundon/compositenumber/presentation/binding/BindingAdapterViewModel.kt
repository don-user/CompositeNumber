package ru.yundon.compositenumber.presentation.binding

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OnOptionClickListener{

    fun onOptionClick(option: Int)
}

@BindingAdapter("numberAsText")
fun numberAsText(textView: TextView, number: Int) {

    textView.text = number.toString()
}

@BindingAdapter("setProgressBar")
fun bindSetProgressBar(progressBar: ProgressBar, percentOfRightAnswer: Int) {
    progressBar.setProgress(percentOfRightAnswer, true)
}

//устанавливаем цвет теста где показывает сколько верных ответов
@BindingAdapter("enoughCountOfRightAnswers")
fun bindEnoughCountOfRightAnswers(textView: TextView, enoughCountOfRightAnswers: Boolean) {

    textView.setTextColor(getColorByState(textView.context, enoughCountOfRightAnswers))
}

@BindingAdapter("enoughPercentOfRightAnswers")
fun bindEnoughPercentOfRightAnswers(
    progressBar: ProgressBar,
    enoughPercentOfRightAnswers: Boolean
) {

    val color = getColorByState(progressBar.context, enoughPercentOfRightAnswers)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId =
        if (goodState) android.R.color.holo_green_light
        else android.R.color.holo_red_light

    return ContextCompat.getColor(context, colorResId)
}

//Слушатель клика на варианты ответов,
// clickListener это функциональный интерфес который принимает Int и ничего не возвращает
@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}