package ru.urfu.droidpractice1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private val articleSwitchState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Жизненный цикл", "MainActivity - onCreate")

        setContent {
            MainActivityScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Жизненный цикл", "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Жизненный цикл", "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Жизненный цикл", "MainActivity - onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Жизненный цикл", "MainActivity - onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Жизненный цикл", "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Жизненный цикл", "MainActivity - onDestroy")
    }
}