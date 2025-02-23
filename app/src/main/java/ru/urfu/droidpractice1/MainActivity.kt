package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }

    fun onClick(view: View?) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

}