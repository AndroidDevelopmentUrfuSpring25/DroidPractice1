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
    private var countLike: Int by mutableIntStateOf(0)

    companion object {
        const val KEY_READ = "read"
        const val KEY_COUNT = "count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                onToOtherScreenClicked = ::onToOtherScreenClicked,
                onToShareClicked = ::onToShareClicked,
                onLikePlus = ::onLikePlus,
                onLikeMinus = ::onLikeMinus,
                read = read,
                countLike = countLike
            )
        }
    }

    override fun onStart() {
        Log.d("Main_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Main_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Main_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Main_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Main_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Main_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, countLike)
        outState.putBoolean(KEY_READ, read)
        Log.d("Main_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        countLike = savedInstanceState.getInt(KEY_COUNT)
        read = savedInstanceState.getBoolean(KEY_READ)
        Log.d("Main_Activity", "onRestoreInstanceState")
    }

    private fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "The Frosty Planet Pack DLC")
        }
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    private fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, read)
        }
        resultLauncher.launch(intent)
    }

    private fun onLikePlus() {
        countLike++
    }

    private fun onLikeMinus() {
        countLike = (countLike - 1).coerceAtLeast(0)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                read = data.getBooleanExtra(KEY_READ, false)
            }
        }
    }
}