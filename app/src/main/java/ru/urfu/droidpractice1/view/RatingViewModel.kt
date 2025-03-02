package ru.urfu.droidpractice1.view

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RatingViewModel : ViewModel() {

    var likesCount = mutableIntStateOf(0)
    var isLiked = mutableStateOf(false)
    var dislikesCount = mutableIntStateOf(0)
    var isDisliked = mutableStateOf(false)

    fun pressLike() {
        Log.i(RatingViewModel::class.toString(), "Like pressed")

        if (isDisliked.value) {
            dislikesCount.value -= 1
            isDisliked.value = false
        }
        isLiked.value = !isLiked.value
        likesCount.value += if (isLiked.value) 1 else -1
    }

    fun pressDislike() {
        Log.i(RatingViewModel::class.toString(), "Dislike pressed")

        if (isLiked.value) {
            likesCount.value -= 1
            isLiked.value = false
        }
        isDisliked.value = !isDisliked.value
        dislikesCount.value += if (isDisliked.value) 1 else -1
    }
}
