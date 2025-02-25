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
import androidx.compose.ui.res.stringArrayResource
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_COOKED
import ru.urfu.droidpractice1.content.MainActivityScreen


class MainActivity : ComponentActivity() {
    private var cooked: Boolean by mutableStateOf(false)
    private var rating: Int by mutableIntStateOf(0)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            cooked = result.data?.getBooleanExtra(KEY_COOKED, false) ?: false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate()")
        setContent {
            MainActivityScreen(
                rating,
                cooked,
                stringArrayResource(id = R.array.article_images),
                ::onClickLike,
                ::onClickDislike,
                ::onClickShare,
                ::onClickRecipe,
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_COOKED, cooked)
        outState.putInt(KEY_RATING, rating)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cooked = savedInstanceState.getBoolean(KEY_COOKED)
        rating = savedInstanceState.getInt(KEY_RATING)
    }

    fun onClickLike() {
        rating++
    }

    fun onClickDislike() {
        /* Рейтинг, по задумке, может уйти в минус. */
        rating--
    }

    fun onClickShare() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.article_link_url))
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun onClickRecipe() {
        val moveIntent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_COOKED, cooked)
        }
        resultLauncher.launch(moveIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()")
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy()")
    }

    companion object {
        const val KEY_RATING = "rating"
    }
}