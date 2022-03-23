package ru.yundon.compositenumber.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.yundon.compositenumber.domain.entity.Level
import java.lang.RuntimeException

class GameViewModelFactory(
    private val level: Level,
    private val application: Application
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelGameFragment::class.java)) {
            return ViewModelGameFragment(application, level) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")

    }
}