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

const val KEY_READ = "isRead"

class MainActivity : ComponentActivity() {
    private var isRead by mutableStateOf(false)

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isRead = data?.getBooleanExtra(KEY_READ, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate()")
        setContent {
            MainActivityScreen(
                isRead = isRead,
                onToOtherScreenClicked = ::onToOtherScreenClicked
            )
        }
    }

    fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, isRead)
        }
        Log.d("MainActivity", "change activity main to second")
        resultLauncher.launch(intent)
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
}