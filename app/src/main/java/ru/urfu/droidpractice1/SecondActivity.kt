package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.addCallback
import androidx.activity.ComponentActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var cooked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cooked = intent.getBooleanExtra(KEY_COOKED, false)
        val contentImages = resources.getStringArray(R.array.recipe_images)

        /* Лучше было использовать цикл, но я не смог найти, как итерироваться по разным id */
        Glide.with(binding.photo1).asBitmap().load(contentImages[0]).into(binding.photo1)
        Glide.with(binding.photo2).asBitmap().load(contentImages[1]).into(binding.photo2)
        Glide.with(binding.photo3).asBitmap().load(contentImages[2]).into(binding.photo3)
        Glide.with(binding.photo4).asBitmap().load(contentImages[3]).into(binding.photo4)
        Glide.with(binding.photo5).asBitmap().load(contentImages[4]).into(binding.photo5)
        Glide.with(binding.photo6).asBitmap().load(contentImages[5]).into(binding.photo6)
        Glide.with(binding.photo7).asBitmap().load(contentImages[6]).into(binding.photo7)

        binding.cookedSwitch.isChecked = cooked
        binding.cookedSwitch.setOnCheckedChangeListener { _, isChecked -> cooked = isChecked }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_COOKED, cooked) })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_COOKED, cooked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cooked = savedInstanceState.getBoolean(KEY_COOKED)
    }

    companion object {
        const val KEY_COOKED = "cooked"
    }
}