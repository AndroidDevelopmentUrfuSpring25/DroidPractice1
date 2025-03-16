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
const val KEY_RATING = "rating"

class MainActivity : ComponentActivity() {
    private var isRead: Boolean by mutableStateOf(false)
    private var rating: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "Create")
        setContent {
            MainActivityScreen(
                isRead,
                rating,
                ::onClickLike,
                ::onClickDislike,
                ::onClickOtherArticle,
                ::onClickShare
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isRead)
        outState.putInt(KEY_RATING, rating)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(KEY_READ)
        rating = savedInstanceState.getInt(KEY_RATING)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "Start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "Destroy")
    }

    fun onClickOtherArticle() {
        val switchIntent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isRead)
        }
        Log.d("MainActivity", "Switching to the second article")
        resultLauncher.launch(switchIntent)
    }

    fun onClickShare() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.article_share))
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun onClickLike() {
        Log.d("MainActivity", "Like is pressed")
        rating++
    }

    fun onClickDislike() {
        Log.d("MainActivity", "Dislike is pressed")
        rating--
    }
}