package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val switchRead = binding.switchRead
        binding.switchRead.isChecked = sharedPreferences.getBoolean("SECOND_ARTICLE_READ", false)

        switchRead.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("SECOND_ARTICLE_READ", isChecked).apply()
        }

        val toolbar: android.widget.Toolbar = binding.toolbar
        setActionBar(toolbar)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

}

