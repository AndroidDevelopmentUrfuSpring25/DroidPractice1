package ru.urfu.droidpractice1.content

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class LikesDislikesViewModel : ViewModel() {
    var state = mutableStateOf(LikeDislikeState())

    fun toggleLike() {
        Log.d(LESSON_LOG, "Inside the like toggle")

        val newLikeCount = if (state.value.isLiked) {
            state.value.likeCount - 1
        } else {
            state.value.likeCount + 1
        }

        val newDislikeCount = if (state.value.isDisliked) {
            state.value.dislikeCount - 1
        } else {
            state.value.dislikeCount
        }

        state.value = state.value.copy(
            likeCount = newLikeCount,
            dislikeCount = newDislikeCount,
            isLiked = !state.value.isLiked,
            isDisliked = false,
            likeColor = if (newLikeCount > 0) Color.Red else Color.Black,
            dislikeColor = if (newDislikeCount > 0) Color.Red else Color.Black
        )
    }

    fun toggleDislike() {
        Log.d(LESSON_LOG, "Inside the dislike toggle")

        val newDislikeCount = if (state.value.isDisliked) {
            state.value.dislikeCount - 1
        } else {
            state.value.dislikeCount + 1
        }

        val newLikeCount = if (state.value.isLiked) {
            state.value.likeCount - 1
        } else {
            state.value.likeCount
        }

        state.value = state.value.copy(
            likeCount = newLikeCount,
            dislikeCount = newDislikeCount,
            isDisliked = !state.value.isDisliked,
            isLiked = false,
            likeColor = if (newLikeCount > 0) Color.Red else Color.Black,
            dislikeColor = if (newDislikeCount > 0) Color.Red else Color.Black
        )
    }
}