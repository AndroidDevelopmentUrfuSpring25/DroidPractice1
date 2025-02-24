package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Glide.with(binding.image1)
            .asBitmap()
            .load("https://img.championat.com/s/732x488/news/big/i/p/aleksandr-ovechkin-nazval-buduschego-kapitana-vashingtona_17403646002007547425.jpg")
            .into(binding.image1)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked}
    }
}