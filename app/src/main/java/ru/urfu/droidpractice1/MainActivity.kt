package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

const val KEY_READ = "isRead"
const val KEY_UP_VOTE = "upVote"
const val KEY_DOWN_VOTE = "downVote"

class MainActivity : ComponentActivity() {
    private var upVoteCount by mutableIntStateOf(0)
    private var downVoteCount by mutableIntStateOf(0)
    private var isRead by mutableStateOf(false)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate()")
        setContent {
            MainActivityScreen(
                upVoteCount,
                downVoteCount,
                isRead,
                ::upVote,
                ::downVote,
                ::onToOtherScreenClicked
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isRead)
        outState.putInt(KEY_UP_VOTE, upVoteCount)
        outState.putInt(KEY_DOWN_VOTE, downVoteCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(KEY_READ)
        upVoteCount = savedInstanceState.getInt(KEY_UP_VOTE)
        downVoteCount = savedInstanceState.getInt(KEY_DOWN_VOTE)
    }

    fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isRead)
        }
        Log.d("MainActivity", "change activity main to second")
        resultLauncher.launch(intent)
    }

    fun upVote() {
        upVoteCount++
    }

    fun downVote() {
        downVoteCount++
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy()")
    }
}