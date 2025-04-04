package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "onCreate()")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val switchRead = binding.switchRead

        switchRead.isChecked = intent.getBooleanExtra(KEY_READ, false)

        Glide.with(binding.photo)
            .asBitmap()
            .load("https://i.pinimg.com/736x/e7/45/ee/e745ee7523b07b89b00f46390be30ec5.jpg")
            .into(binding.photo)

        binding.toolbar.setNavigationOnClickListener {
            Log.d("SecondActivity", "change activity second to main")
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent().apply {
                    putExtra(KEY_READ, switchRead.isChecked)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy()")
    }
}