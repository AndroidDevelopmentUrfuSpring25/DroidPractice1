package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.ui.theme.DataHolder

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Загрузка картинки
        val imageView: ImageView = findViewById(R.id.Fatutu_2_img)
        val imageUrl = "https://cdn.donmai.us/sample/d2/bb/__fatutu_reverse_1999__sample-d2bbbdc0dfcd7dba3b6a0a854e9ef943.jpg"
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        val switch = findViewById<Switch>(R.id.switch_read)
        switch.isChecked = DataHolder.readSecondActivity

        binding.toolbar.setNavigationOnClickListener {
            DataHolder.readSecondActivity = switch.isChecked
            MainActivity.needToRecreate = true
            finish()
        }
        Log.i("SecondActivity", "onCreate() called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("SecondActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("SecondActivity", "onRestart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("SecondActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("SecondActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("SecondActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SecondActivity", "onDestroy() called")
    }
}