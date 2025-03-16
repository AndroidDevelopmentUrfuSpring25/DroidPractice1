package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.content.LESSON_LOG
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private var checked: Boolean = false
    private lateinit var binding: ActivitySecondBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(LESSON_LOG, "Setting image content inside second activity")

        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/NVIDIA_logo.svg/1462px-NVIDIA_logo.svg.png")
            .into(binding.myImageView)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }


        binding.mySwitch.setOnCheckedChangeListener { _, isChecked ->
            checked = isChecked
        }

        onBackPressedDispatcher.addCallback(this) {
            Log.d(LESSON_LOG, "Switch pressed inside second activity")
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    companion object {
        const val KEY_READ = "READ"
    }
}