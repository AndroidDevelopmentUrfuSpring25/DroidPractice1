package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var readSwitch: Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        readSwitch = findViewById(R.id.readSwitch)
        val sharedPrefs = getSharedPreferences("SwitchPrefs", MODE_PRIVATE)
        readSwitch.isChecked = sharedPrefs.getBoolean("isRead", false)
        readSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("isRead", isChecked).apply()
        }

        val imageView = findViewById<ImageView>(R.id.article_image);
        val imageUrl = "https://img.championat.com/s/732x488/news/big/a/j/totti-v-italii-stalo-slishko.jpg";

        Glide.with(this)
            .load(imageUrl)
            .into(imageView);

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val resultIntent = Intent().apply {
                    putExtra("isRead", readSwitch.isChecked)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)

        Log.d("SecondActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart")
    }
}