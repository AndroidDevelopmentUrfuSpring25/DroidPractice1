package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var isSecondArticleRead = false
    private var likeCount = 0
    private var dislikeCount = 0
    private var isLiked = false
    private var isDisliked = false

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra("isRead", false) ?: false
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        updateUI()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun updateUI() {
        setContent {
            MainActivityScreen(
                onNavigateToSecondArticle = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra("isRead", isSecondArticleRead)
                    }
                    secondActivityLauncher.launch(intent)
                },
                isSecondArticleRead = isSecondArticleRead,
                likeCount = likeCount,
                dislikeCount = dislikeCount,
                isLiked = isLiked,
                isDisliked = isDisliked,
                onLikeClick = {
                    handleLikeClick()
                },
                onDislikeClick = {
                    handleDislikeClick()
                },
                onShareClick = {
                    shareArticle()
                }
            )
        }
    }

    private fun handleLikeClick() {
        if (isLiked) {
            likeCount--
            isLiked = false
        } else {
            if (isDisliked) {
                isDisliked = false
                dislikeCount--
            }
            likeCount++
            isLiked = true
        }
        updateUI()
    }

    private fun handleDislikeClick() {
        if (isDisliked) {
            dislikeCount--
            isDisliked = false
        } else {
            if (isLiked) {
                isLiked = false
                likeCount--
            }
            dislikeCount++
            isDisliked = true
        }
        updateUI()
    }

    private fun shareArticle() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_SUBJECT,
                "Самый легендарный гранд-финал в истории Dota 2 — что случилось в матче Tundra и Team Spirit на DreamLeague Season 25"
            )
            putExtra(
                Intent.EXTRA_TEXT,
                "Несмотря на то что по расписанию DreamLeague Season 25 по Dota 2 уже должен был завершиться, мы всё еще не знаем чемпиона. Всё из-за того, что во время гранд-финала ивента Team Spirit подверглась DDoS-атаке. В результате серию пришлось прервать. Для тех, кто не следил за развитием истории, мы подготовили хронологию событий. "
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Поделиться статьей через..."))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt("likeCount", likeCount)
        outState.putInt("dislikeCount", dislikeCount)
        outState.putBoolean("isLiked", isLiked)
        outState.putBoolean("isDisliked", isDisliked)
        outState.putBoolean("isSecondArticleRead", isSecondArticleRead)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
        likeCount = savedInstanceState.getInt("likeCount", 0)
        dislikeCount = savedInstanceState.getInt("dislikeCount", 0)
        isLiked = savedInstanceState.getBoolean("isLiked", false)
        isDisliked = savedInstanceState.getBoolean("isDisliked", false)
        isSecondArticleRead = savedInstanceState.getBoolean("isSecondArticleRead", false)
        updateUI()
    }
}