package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.compose.setContent
import coil.load
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val articleSwitch = findViewById<Switch>(R.id.articleSwitchRead)
        val initialState = intent.getBooleanExtra("switchState", false)
        articleSwitch.isChecked = initialState

        articleSwitch.setOnCheckedChangeListener { _, isChecked ->
            val intent = Intent()
            intent.putExtra("switchState", isChecked)
            setResult(Activity.RESULT_OK, intent)
        }

        val articleImage = findViewById<ImageView>(R.id.articleImage)
        articleImage.load("https://img.championat.com/s/732x488/news/big/r/b/bavariya-razgromila-ajntraht-v-bundeslige-kejn-vyshel-na-zamenu-vo-vtorom-tajme_17403352361659628015.jpg") {
            error(R.drawable.ic_launcher_background)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Жизненный цикл", "SecondActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Жизненный цикл", "SecondActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Жизненный цикл", "SecondActivity - onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Жизненный цикл", "SecondActivity - onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Жизненный цикл", "SecondActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Жизненный цикл", "SecondActivity - onDestroy")
    }
}