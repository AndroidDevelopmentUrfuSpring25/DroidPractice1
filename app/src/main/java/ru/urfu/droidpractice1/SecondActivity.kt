package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var read: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        read = intent.getBooleanExtra(KEY_READ, false)
        binding.readCheck.isChecked = read
        binding.readCheck.setOnCheckedChangeListener { _, isChecked -> read = isChecked }

        Glide.with(binding.photo)
            .asBitmap()
            .load(resources.getString(R.string.image_url))
            .into(binding.photo)

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, read) })
            finish()
        }

        Log.d("SecondActivity", "onCreate()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, read)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean(KEY_READ)
    }

    companion object {
        const val KEY_READ = "read"
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy()")
    }
}
