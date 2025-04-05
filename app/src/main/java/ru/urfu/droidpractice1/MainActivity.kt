package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.ArticleScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticleScreen()
        }

        Log.d("FirstActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FirstActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FirstActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FirstActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FirstActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FirstActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FirstActivity", "onRestart")
    }
}