package ru.urfu.droidpractice1.content

import androidx.compose.ui.graphics.Color

data class LikeDislikeState(
    val likeCount: Int = 0,
    val dislikeCount: Int = 0,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val likeColor: Color = Color.Black,
    val dislikeColor: Color = Color.Black
)