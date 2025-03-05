package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isRead by mutableStateOf(false)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isRead)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRead = savedInstanceState?.getBoolean(KEY_READ) ?: false
        setContent {
            MainActivityScreen(
                onShareClick = { shareArticle() },
                onOtherArticleClick = { openNextArticle() },
                isRead = isRead
            )
        }
    }

    override fun onStart() {
        super.onStart()

        println("MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()

        println("MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()

        println("MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()

        println("MainActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        println("MainActivity: onDestroy")
    }


    private fun shareArticle() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.article_share))
        }

        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_title))
        startActivity(shareIntent)
    }

    private fun openNextArticle() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isRead)
        }

        resultLauncher.launch(intent)
    }
}