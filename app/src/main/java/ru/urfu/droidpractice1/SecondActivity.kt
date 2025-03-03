package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "onCreate() called")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isReadChecked = intent.getBooleanExtra("isReadChecked", false)
        binding.switchRead.isChecked = isReadChecked

        Glide.with(binding.image1)
            .asBitmap()
            .load("https://media.kudago.com/thumbs/xl/images/list/8e/33/8e33b6c6f67d8423922ab7ac7a431b65.jpg")
            .into(binding.image1)

        binding.toolbar.setNavigationOnClickListener { finishActivity() }

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
            setResultData()
        }
    }

    private fun setResultData() {
        val resultIntent = Intent()
        resultIntent.putExtra("isReadChecked", isReadChecked)
        setResult(Activity.RESULT_OK, resultIntent)
    }

    private fun finishActivity() {
        setResultData()
        finish()
    }
    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart() called")
    }
}