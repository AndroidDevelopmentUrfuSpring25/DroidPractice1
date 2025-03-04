package ru.urfu.droidpractice1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val isSecondArticleRead = sharedPreferences.getBoolean("SECOND_ARTICLE_READ", false)
        setContent {
            MainActivityScreen(isSecondArticleRead)
        }
    }
    override fun onResume() {
        super.onResume()
        val isSecondArticleRead = sharedPreferences.getBoolean("SECOND_ARTICLE_READ", false)
        setContent {
            MainActivityScreen(isSecondArticleRead)
        }
    }
}