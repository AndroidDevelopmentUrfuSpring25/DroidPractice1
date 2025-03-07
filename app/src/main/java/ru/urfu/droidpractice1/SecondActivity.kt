package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isReadChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        isReadChecked = intent.getBooleanExtra(KEY_IS_LESSON_READ, false)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        onBackPressedDispatcher.addCallback(this){
            val intent = Intent().apply {
                putExtra(KEY_IS_LESSON_READ, isReadChecked)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        Glide.with(binding.photo1)
            .asBitmap()
            .load(getString(R.string.lesson_photo_1))
            .into(binding.photo1)
        Glide.with(binding.photo2)
            .asBitmap()
            .load(getString(R.string.lesson_photo_2))
            .into(binding.photo2)
        Glide.with(binding.photo3)
            .asBitmap()
            .load(getString(R.string.lesson_photo_3))
            .into(binding.photo3)

        binding.readSwitch.isChecked = isReadChecked
        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            isReadChecked = isChecked
        }

        Log.d("SecondActivity", "Вызов onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "Вызов onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "Вызов onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "Вызов onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "Вызов onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "Вызов onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "Вызов onDestroy()")
    }

    companion object {
        const val KEY_IS_LESSON_READ = "KEY_IS_LESSON_READ"
    }
}