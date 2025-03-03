package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

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

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}