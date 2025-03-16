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
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var read: Boolean by mutableStateOf(false)
    private var rating: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(MainActivity::class.toString(), "Create")

        setContent {
            Log.i(MainActivity::class.toString(), "Set first activity content")
            MainActivityScreen(
                read,
                rating,
                resultLauncher,
                this::onClickLike,
                this::onClickDislike
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(MainActivity::class.toString(), "Start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(MainActivity::class.toString(), "Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(MainActivity::class.toString(), "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(MainActivity::class.toString(), "Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(MainActivity::class.toString(), "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(MainActivity::class.toString(), "Destroy")
    }

    private fun onClickLike() {
        Log.i(MainActivity::class.toString(), "Like")
        rating++
    }

    private fun onClickDislike() {
        Log.i(MainActivity::class.toString(), "Dislike")
        rating--
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                read = data?.getBooleanExtra(KEY_READ, false) == true
            }
        }
}