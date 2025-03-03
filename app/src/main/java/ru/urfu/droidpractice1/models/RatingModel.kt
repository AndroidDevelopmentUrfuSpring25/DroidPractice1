package ru.urfu.droidpractice1.models

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RatingModel : ViewModel() {

    var likeIconCounter = mutableIntStateOf(0)
    var isLiked = mutableStateOf(false)

    var dislikeIconCounter = mutableIntStateOf(0)
    var isDisliked = mutableStateOf(false)

    fun pressLike() {

        log("Like is pressed")

        if (isDisliked.value) {
            dislikeIconCounter.value -= 1
            isDisliked.value = false
        }

        isLiked.value = !isLiked.value

        if (isLiked.value)
            likeIconCounter.value += 1
        else
            likeIconCounter.value -= 1
    }

    fun pressDislike() {
        log("Dislike is pressed")

        if (isLiked.value) {
            likeIconCounter.value -= 1
            isLiked.value = false
        }

        isDisliked.value = !isDisliked.value

        if (isDisliked.value)
            dislikeIconCounter.value += 1
        else
            dislikeIconCounter.value -= 1
    }

    fun log(message: String){
        Log.i(RatingModel::class.toString(), message)
    }

}