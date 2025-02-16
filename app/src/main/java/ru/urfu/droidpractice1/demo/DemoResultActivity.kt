package ru.urfu.droidpractice1.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class DemoResultActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = intent.getStringExtra(SELECTED_MODEL_KEY)
        setContent {
            Text("Спасибо за выбор $result!")
        }
    }

    companion object {
        const val SELECTED_MODEL_KEY = "SELECTED_MODEL_KEY"
    }
}