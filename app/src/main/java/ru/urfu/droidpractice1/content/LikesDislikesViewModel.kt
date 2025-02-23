package ru.urfu.droidpractice1.content

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LikesDislikesViewModel : ViewModel() {
    var likeCount = mutableIntStateOf(0)
    var isLiked = mutableStateOf(false)
    var dislikeCount = mutableIntStateOf(0)
    var isDisliked = mutableStateOf(false)

    fun toggleLike() {
        Log.d(LESSON_LOG, "Inside the like toggle")

        if (isLiked.value) {
            likeCount.value -= 1
        }
        isLiked.value = !isLiked.value
        if (isLiked.value) {
            likeCount.value += 1
        }
        if (isDisliked.value) {
            dislikeCount.value -= 1
            isDisliked.value = false
        }
    }

    fun toggleDislike() {
        Log.d(LESSON_LOG, "Inside the dislike toggle")

        if (isDisliked.value) {
            dislikeCount.value -= 1
        }
        isDisliked.value = !isDisliked.value
        if (isDisliked.value) {
            dislikeCount.value += 1
        }
        if (isLiked.value) {
            likeCount.value -= 1
            isLiked.value = false
        }
    }
}
