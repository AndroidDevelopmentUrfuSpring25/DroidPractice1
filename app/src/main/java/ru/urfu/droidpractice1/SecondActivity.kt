package ru.urfu.droidpractice1

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var prefs: SharedPreferences

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

        prefs = getSharedPreferences("article_prefs", MODE_PRIVATE)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(this)
            .load(IMAGE_URL)
            .into(binding.articleImage)

        binding.readSwitch.isChecked = prefs.getBoolean(READ_STATUS_KEY, false)
        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(READ_STATUS_KEY, isChecked).apply()
            Log.d(TAG, "Переключатель 'Прочитано' изменён: $isChecked")
        }
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
}
