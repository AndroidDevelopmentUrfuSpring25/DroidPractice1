package ru.urfu.droidpractice1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VoteViewModel : ViewModel() {
    private var upVoteCount by mutableIntStateOf(0)
    private var downVoteCount by mutableIntStateOf(0)

    val getUpVoteCount: Int
        get() = upVoteCount

    val getDownVoteCount: Int
        get() = downVoteCount

    fun upVote() {
        upVoteCount++
    }

    fun downVote() {
        downVoteCount++
    }
}