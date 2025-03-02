package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "Create")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val readSwitcher = binding.readSwitcher

        readSwitcher.isChecked = intent.getBooleanExtra(KEY_READ, false)

        Glide.with(binding.image)
            .asBitmap()
            .load("https://www.ixbt.com/img/n1/news/2024/11/2/INTEl-ARC-BATTLEMAGE-4-1200x650_large.jpg")
            .into(binding.image)

        binding.toolbar.setNavigationOnClickListener {
            Log.d("SecondActivity", "Switching to the main article")
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, readSwitcher.isChecked) })
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "Start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "Restart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "Destroy")
    }
}