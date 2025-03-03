package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import android.content.Intent
import android.os.Bundle
import android.util.Log
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide

class SecondActivity : ComponentActivity() {

    companion object {
        var keyRead = "readSecondArticle"
    }

    private lateinit var binding: ActivitySecondBinding
    private val imageLink = "https://img.championat.com/s/732x488/news/big/i/p/aleksandr-ovechkin-nazval-buduschego-kapitana-vashingtona_17403646002007547425.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(SecondActivity::class.toString(), "Second activity inizialization")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.switchRead.isChecked = intent.getBooleanExtra(keyRead, false)

        loadImage(imageLink)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            Log.i(SecondActivity::class.toString(), "Read switch pressed")

            setResult(RESULT_OK, Intent().apply { putExtra(keyRead, binding.switchRead.isChecked) })
            finish()
        }
    }

    private fun loadImage(imageLink: String){
        Log.i(SecondActivity::class.toString(), "Loading image content in second activity")

        Glide.with(binding.image1)
            .asBitmap()
            .load(imageLink)
            .into(binding.image1)
    }
}