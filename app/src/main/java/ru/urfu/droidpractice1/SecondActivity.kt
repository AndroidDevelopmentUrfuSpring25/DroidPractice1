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
            .load("https://img.championat.com/s/1350x900/news/big/u/i/igrok-krylev-kostanca-obyasn.jpg")
            .into(binding.image1)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Отслеживание состояния переключателя "Прочитано"
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
        }
    }
}