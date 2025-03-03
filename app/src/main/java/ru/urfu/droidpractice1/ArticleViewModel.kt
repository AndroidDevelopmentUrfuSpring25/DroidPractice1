package ru.urfu.droidpractice1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleViewModel : ViewModel() {
    private val _likes = MutableStateFlow(0)
    val likes: StateFlow<Int> = _likes.asStateFlow()

    private val _dislikes = MutableStateFlow(0)
    val dislikes: StateFlow<Int> = _dislikes.asStateFlow()

    private val _isArticleRead = MutableStateFlow(false)
    val isArticleRead: StateFlow<Boolean> = _isArticleRead.asStateFlow()

    fun incrementLikes() {
        _likes.value++
    }

    fun incrementDislikes() {
        _dislikes.value++
    }

    fun setArticleRead(read: Boolean) {
        _isArticleRead.value = read
    }
} 