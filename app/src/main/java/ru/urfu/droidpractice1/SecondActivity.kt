package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var switchRead: Switch
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        val imageView: ImageView = findViewById(R.id.nintendo_first)
        imageView.load("https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_65ca99a034fdc3328d739dc2_65ca99aa34fdc3328d73a17d/scale_1200") {
            placeholder(R.drawable.placeholder)
            crossfade(true)
        }
        switchRead = findViewById(R.id.readswitch)
        isRead = intent.getBooleanExtra("isRead", false)
        switchRead.isChecked = isRead
        switchRead.setOnCheckedChangeListener{ _, isChecked ->
            isRead = isChecked
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent().apply {
                    putExtra("isRead", isRead)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }
}