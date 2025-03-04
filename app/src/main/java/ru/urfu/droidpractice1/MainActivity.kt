package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var read: Boolean by mutableStateOf(false)
    private var rating: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            read = result.data?.getBooleanExtra(KEY_READ, false) ?: false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::onClickMore,
                ::onClickShare,
                ::onClickLike,
                ::onClickDislike,
                read,
                rating
            )
        }
        Log.d("MainActivity", "onCreate()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, read)
        outState.putInt(KEY_RATING, rating)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean(KEY_READ)
        rating = savedInstanceState.getInt(KEY_RATING)
    }

    fun onClickMore() {
        val moveIntent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, read)
        }
        resultLauncher.launch(moveIntent)
    }

    fun onClickShare() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT, getString(R.string.view_article_title_name)
                        + "\n" + getString(R.string.article_url)
            )
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun onClickLike() {
        rating++
    }

    fun onClickDislike() {
        rating--
    }

    companion object {
        const val KEY_READ = "read"
        const val KEY_RATING = "rating"
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy()")
    }
}

