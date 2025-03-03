package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.keyRead
import ru.urfu.droidpractice1.content.MainActivityScreen

public val ArticleLink = "https://www.championat.com/hockey/news-5921648-37-letnij-sidni-krosbi-voshyol-v-top-10-igrokov-po-kolichestvu-assistov-v-istorii-nhl.html"

class MainActivity : ComponentActivity() {

    public var isRead by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Log.i(MainActivity::class.toString(), "Setting main activity content")
            MainActivityScreen(isRead, resultLauncher)
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(keyRead, false) == true
            }
        }
}