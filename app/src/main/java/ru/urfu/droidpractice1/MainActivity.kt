package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this)
        }
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, "Разоблачение")
        startActivity(intent)
    }

    override fun onToShareClicked() {

    }
}