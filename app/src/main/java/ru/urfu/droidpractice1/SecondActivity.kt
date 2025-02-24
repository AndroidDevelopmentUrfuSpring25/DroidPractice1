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

        val imageUrl = "https://img.championat.com/s/732x488/news/big/y/g/dementev-rasskazal-pochemu-pauza-v-chempionate-nhl-polozhitelno-skazalas-na-igre-ovechkina_17403872371052613650.jpg"

        Glide.with(this@SecondActivity)
            .asBitmap()
            .load(imageUrl)
            .into(binding.photo)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked}
    }
}