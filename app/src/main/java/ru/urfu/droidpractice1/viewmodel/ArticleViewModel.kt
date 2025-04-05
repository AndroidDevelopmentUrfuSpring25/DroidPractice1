package ru.urfu.droidpractice1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(context: Context) : ViewModel() {
    private val sharedPreferences = context.getSharedPreferences("ArticlePrefs", Context.MODE_PRIVATE)

    private val _likes = MutableStateFlow(sharedPreferences.getInt("likes", 0))
    val likes: StateFlow<Int> = _likes

    private val _dislikes = MutableStateFlow(sharedPreferences.getInt("dislikes", 0))
    val dislikes: StateFlow<Int> = _dislikes

    fun like() {
        _likes.value++
        saveCounters()
    }

    fun dislike() {
        _dislikes.value++
        saveCounters()
    }

    private fun saveCounters() {
        viewModelScope.launch {
            sharedPreferences.edit()
                .putInt("likes", _likes.value)
                .putInt("dislikes", _dislikes.value)
                .apply()
        }
    }
}