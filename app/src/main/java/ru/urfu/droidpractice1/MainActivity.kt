package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen


class MainActivity : ComponentActivity() {
    private var cooked: Boolean by mutableStateOf(false)
    private var rating: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::onClickLike,
                ::onClickDislike,
                ::onClickShare,
                ::onClickRecipe,
                cooked,
                rating
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

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            cooked = result.data?.getBooleanExtra(KEY_COOKED, false) ?: false
        }

    companion object {
        const val KEY_COOKED = "cooked"
        const val KEY_RATING = "rating"
    }
}