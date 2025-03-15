package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private val articleReadState = mutableStateOf(false)

    private val secondActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val readStatus = result.data?.getBooleanExtra("read_status", false) ?: false
            articleReadState.value = readStatus
            Log.d("MainActivity", "read_status: $readStatus")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")

        if (savedInstanceState != null) {
            articleReadState.value = savedInstanceState.getBoolean("read_status", false)
            Log.d("MainActivity", "read_status: ${articleReadState.value}")
        }
        setContent {
            MainActivityScreen(
                articleRead = articleReadState.value,
                onArticleClick = {
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra("read_status", articleReadState.value)
                    }
                    secondActivityLauncher.launch(intent)
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("read_status", articleReadState.value)
        Log.d("MainActivity", "read_status ${articleReadState.value}")
    }
}
