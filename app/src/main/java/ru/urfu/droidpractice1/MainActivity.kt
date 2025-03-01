package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var count = 0
    private var selected = 0
    private var isRead by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isRead = savedInstanceState?.getBoolean("isRead") ?: false
        setContent {
            val restoredCount = savedInstanceState?.getInt("count") ?: 0
            val restoredSelected = savedInstanceState?.getInt("selected") ?: 0
            MainActivityScreen(
                isRead,
                count = restoredCount,
                selected = restoredSelected,
                onStateChanged = { newCount: Int, newSelected: Int ->
                    count = newCount
                    selected = newSelected
                    savedInstanceState?.apply {
                        putInt("count", newCount)
                        putInt("selected", newSelected)
                    }
                }
            ){
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("isRead", isRead)
                startActivityForResult(intent, ARTICLE_REQUEST_CODE)
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isRead", isRead)
        outState.putInt("count", count)
        outState.putInt("selected", selected)
    }
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ARTICLE_REQUEST_CODE && resultCode == RESULT_OK) {
            isRead = data?.getBooleanExtra("isRead", false) ?: false
        }
    }

    companion object {
        private const val ARTICLE_REQUEST_CODE = 1
    }
}
