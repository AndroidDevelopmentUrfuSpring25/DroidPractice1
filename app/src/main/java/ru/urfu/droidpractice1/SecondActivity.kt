package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val articleSwitchRead = findViewById<Switch>(R.id.articleSwitchRead)
        articleSwitchRead.setOnCheckedChangeListener { _, isChecked ->
            val switchIntent = Intent().apply {
                putExtra("articleSwitchState", isChecked)
            }
        }

        val articleImage = findViewById<ImageView>(R.id.articleImage)
        articleImage.load("https://img.championat.com/s/732x488/news/big/r/b/bavariya-razgromila-ajntraht-v-bundeslige-kejn-vyshel-na-zamenu-vo-vtorom-tajme_17403352361659628015.jpg") {
            error(R.drawable.ic_launcher_background)
        }
    }
}