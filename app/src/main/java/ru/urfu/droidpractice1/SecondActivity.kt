package ru.urfu.droidpractice1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private val TAG = "SecondActivity"
    private lateinit var binding: ActivitySecondBinding
    private var isArticleRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        isArticleRead = intent.getBooleanExtra("isRead", false)
        
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.articleImage)
            .load("https://media.discordapp.net/attachments/914593555526320151/1346272423317864579/a5d67bfb-ba27-45ce-b326-d316ff45cf4e.png?ex=67c79566&is=67c643e6&hm=73e810f243596789c4e1bba5e50a9788161523ddeccb67b8e9834cc334c8d43d&=&format=webp&quality=lossless")
            .into(binding.articleImage)

        binding.switchRead.isChecked = isArticleRead

        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            isArticleRead = isChecked
            sendResultBack()
        }

        onBackPressedDispatcher.addCallback(this ){
            sendResultBack()
            finish()
        }

    }
    
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
    
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putBoolean("isArticleRead", isArticleRead)
    }
    
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
        isArticleRead = savedInstanceState.getBoolean("isArticleRead", false)
        binding.switchRead.isChecked = isArticleRead
    }

    private fun sendResultBack() {
        val resultIntent = Intent().apply {
            putExtra("isRead", isArticleRead)
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }


}