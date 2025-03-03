package ru.urfu.droidpractice1

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isReadValue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(isRead = isReadValue)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        isReadValue = sharedPrefs.getBoolean("isRead", false)
        setContent {
            MainActivityScreen(isRead = isReadValue)
        }
    }
}