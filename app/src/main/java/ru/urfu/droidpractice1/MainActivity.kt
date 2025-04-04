package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import ru.urfu.droidpractice1.content.MainActivityScreen
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val readSecondActivity = intent.getBooleanExtra("read_second_activity", false)
        setContent {
            MainActivityScreen()
        }
        Log.i("MainActivity", "onCreate() called")
    }

    companion object {
        var needToRecreate: Boolean = false
    }

    override fun onResume() {
        super.onResume()
        if (needToRecreate) {
            needToRecreate = false
            recreate()
        }
        Log.i("MainActivity", "onResume() called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart() called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }
}