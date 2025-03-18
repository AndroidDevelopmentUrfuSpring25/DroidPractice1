package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen


class MainActivity : ComponentActivity() {
    private var isReadChecked = mutableStateOf(true)

    companion object {
        const val EXTRA_IS_READ_CHECKED = "isReadChecked"
        const val RESULT_OK = Activity.RESULT_OK
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                isReadChecked.value = data?.getBooleanExtra(EXTRA_IS_READ_CHECKED, false) ?: false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            isReadChecked.value = savedInstanceState.getBoolean("isReadChecked", true)
        }
        Log.d("MainActivity", "onCreate() called")
        setContent {
            MainActivityScreen(isReadChecked) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("isReadChecked", isReadChecked.value)
                startForResult.launch(intent)
            }
        }
    }
    //Для сохранения состояния перед уничтожением активити
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isReadChecked", isReadChecked.value)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart() called")
    }
}