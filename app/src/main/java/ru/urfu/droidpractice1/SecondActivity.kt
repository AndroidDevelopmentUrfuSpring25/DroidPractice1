package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    companion object {
        const val KEY_IS_READ = "SECOND_ARTICLE_READ"
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isReadChecked = intent.getBooleanExtra(KEY_IS_READ, false)
        binding.switchRead.isChecked = isReadChecked

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_IS_READ, isReadChecked) })
        }

        binding.articleImage.load("https://img.championat.com/s/732x488/news/big/t/w/andrej-rublyov_17404098301814299990.jpg")
        binding.article1image.load("https://img.championat.com/i/k/q/1740409651481784465.jpg")

        setActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}