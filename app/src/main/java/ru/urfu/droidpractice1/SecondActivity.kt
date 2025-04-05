package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.model.ARTICLE_2_HERO_IMAGE_URL

const val KEY_READ = "isRead"

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val readSwitch = binding.switchRead

        readSwitch.isChecked = intent.getBooleanExtra(KEY_READ, false)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent().apply {
                    putExtra(KEY_READ, readSwitch.isChecked)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        })

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        Glide.with(binding.root)
            .load(ARTICLE_2_HERO_IMAGE_URL)
            .into(binding.heroImg)
    }

    override fun onStart() {
        super.onStart()

        println("SecondActivity: onStart")
    }

    override fun onResume() {
        super.onResume()

        println("SecondActivity: onResume")
    }

    override fun onPause() {
        super.onPause()

        println("SecondActivity: onPause")
    }

    override fun onStop() {
        super.onStop()

        println("SecondActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        println("SecondActivity: onDestroy")
    }
}