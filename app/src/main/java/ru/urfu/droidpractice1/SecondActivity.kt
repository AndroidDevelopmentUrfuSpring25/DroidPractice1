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

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.photo1)
            .asBitmap()
            .load(getString(R.string.article_2_photo_1))
            .into(binding.photo1)
        Glide.with(binding.photo2)
            .asBitmap()
            .load(getString(R.string.article_2_photo_2))
            .into(binding.photo2)
        Glide.with(binding.photo3)
            .asBitmap()
            .load(getString(R.string.article_2_photo_3))
            .into(binding.photo3)

        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_SECOND_ARTICLE_READ, isReadChecked)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val IS_SECOND_ARTICLE_READ = "IS_SECOND_ARTICLE_READ"
    }
}