package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.model.ARTICLE2_IMAGE_URL

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.photo)
            .asBitmap()
            .load(ARTICLE2_IMAGE_URL)
            .into(binding.photo)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
        }


    }
}