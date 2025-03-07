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
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_IS_LESSON_READ

class MainActivity : ComponentActivity() {
    private var rating: Int by mutableIntStateOf(0)
    private var isLessonRead: Boolean by mutableStateOf(false)

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                isLessonRead = result.data?.getBooleanExtra(KEY_IS_LESSON_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                rating = rating,
                isLessonRead = isLessonRead,
                onShareClick = ::onShareClick,
                onUpvoteClick = ::onUpvoteClick,
                onDownvoteClick = ::onDownvoteClick,
                onLessonClick = ::onLessonClick
            )
        }

        Log.d("MainActivity", "Вызов onCreate()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        rating = savedInstanceState.getInt(KEY_RATING)
        isLessonRead = savedInstanceState.getBoolean(KEY_IS_LESSON_READ)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_RATING, rating)
        outState.putBoolean(KEY_IS_LESSON_READ, isLessonRead)
        super.onSaveInstanceState(outState)
    }

    private fun onShareClick(text: String) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun onUpvoteClick() {
        rating++
    }

    private fun onDownvoteClick() {
        rating--
    }

    private fun onLessonClick() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_IS_LESSON_READ, isLessonRead)
        }
        activityLauncher.launch(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "Вызов onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "Вызов onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Вызов onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "Вызов onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "Вызов onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Вызов onDestroy()")
    }

    companion object {
        private const val KEY_RATING = "KEY_RATING"
    }
}