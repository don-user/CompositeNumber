package ru.yundon.compositenumber.domain.entity

import java.io.Serializable

data class GameSettings (

    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeSeconds: Int

    ) : Serializable