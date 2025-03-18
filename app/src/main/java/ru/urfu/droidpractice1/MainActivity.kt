package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import ru.urfu.droidpractice1.SecondActivity.Companion.keyRead
import ru.urfu.droidpractice1.content.MainActivityScreen

val ArticleLink =
    "https://www.championat.com/hockey/news-5921648-37-letnij-sidni-krosbi-voshyol-v-top-10-igrokov-po-kolichestvu-assistov-v-istorii-nhl.html"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isRead by rememberSaveable { mutableStateOf(false) }

            val updateReadStatus: (Boolean) -> Unit = { isRead = it }

            val resultLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data: Intent? = result.data
                        updateReadStatus(data?.getBooleanExtra(keyRead, false) == true)
                    }
                }

            Log.d(MainActivity::class.toString(), "Setting main activity content")
            MainActivityScreen(isRead, resultLauncher)
        }
    }
}
