package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.LESSON_LOG
import ru.urfu.droidpractice1.content.MainActivityScreen

const val LIFECYCLE_TAG = "LIFECYCLE_LOG"

class MainActivity : ComponentActivity() {
    private var read: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Log.d(LESSON_LOG, "Setting first activity content")
            MainActivityScreen(read, resultLauncher)
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(LESSON_LOG, "inside register for activity result")
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(LIFECYCLE_TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFECYCLE_TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFECYCLE_TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFECYCLE_TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LIFECYCLE_TAG, "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LIFECYCLE_TAG, "onRestart called")
    }
}