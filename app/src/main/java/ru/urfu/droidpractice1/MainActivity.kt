package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isSecondArticleRead = false

    val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(SecondActivity.KEY_IS_READ, false) ?: false
            setContent {
                MainActivityScreen(isSecondArticleRead)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            isSecondArticleRead = it.getBoolean("SECOND_ARTICLE_READ", false)
        }
        setContent {
            MainActivityScreen(isSecondArticleRead)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("SECOND_ARTICLE_READ", isSecondArticleRead)
    }

    fun updateSecondArticleReadStatus(isRead: Boolean) {
        isSecondArticleRead = isRead
        setContent {
            MainActivityScreen(isSecondArticleRead)
        }
    }
}