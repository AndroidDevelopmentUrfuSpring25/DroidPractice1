package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var isRead by mutableStateOf(false)

    private val prefs by lazy { getSharedPreferences("settings", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        isRead = prefs.getBoolean("isRead", false)

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
            putExtra(Intent.EXTRA_TEXT, "Лукас Вера сравнил Химки с Челси")
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openNextArticle() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}