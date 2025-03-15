package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var readStatus: Boolean = false

    companion object {
        private const val TAG = "SecondActivity"
        private const val READ_STATUS_KEY = "read_status"
        private const val IMAGE_URL = "https://img.championat.com/s/732x488/news/big/e/o/skazhu-odno-my-budem-gotovy-simeone-o-madridskom-derbi-v-1-8-finala-ligi-chempionov_1740169817524784904.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readStatus = savedInstanceState?.getBoolean(READ_STATUS_KEY, false)
            ?: intent.getBooleanExtra(READ_STATUS_KEY, false)
        binding.readSwitch.isChecked = readStatus

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            readStatus = isChecked
            Log.d(TAG, "Switch changed: $readStatus")
        }

        Glide.with(this)
            .load(IMAGE_URL)
            .into(binding.articleImage)

        binding.toolbar.setNavigationOnClickListener {
            returnResultAndFinish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        returnResultAndFinish()
    }

    private fun returnResultAndFinish() {
        val resultIntent = Intent().apply {
            putExtra(READ_STATUS_KEY, readStatus)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(READ_STATUS_KEY, readStatus)
        Log.d(TAG, "onSaveInstanceState: saved read_status = $readStatus")
    }
}
