package ru.urfu.droidpractice1

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import android.os.Bundle
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("article_prefs", Context.MODE_PRIVATE)

        val imageUrl = "https://img.championat.com/s/732x488/news/big/y/g/dementev-rasskazal-pochemu-pauza-v-chempionate-nhl-polozhitelno-skazalas-na-igre-ovechkina_17403872371052613650.jpg"

        Glide.with(this@SecondActivity)
            .asBitmap()
            .load(imageUrl)
            .into(binding.photo)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.switchRead.isChecked = sharedPreferences.getBoolean("is_second_article_read", false)
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("is_second_article_read", isChecked).apply()
        }
    }
}