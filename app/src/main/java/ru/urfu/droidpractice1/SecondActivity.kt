package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

private const val CAT_IMAGE_LINK =
    "https://static.wikia.nocookie.net/catspedia/images/7/7f/1383579080343.jpg/revision/latest?cb=20140202100338&path-prefix=ru"

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(SecondActivity::class.toString(), "Initializing second activity")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.readSwitch.isChecked = intent.getBooleanExtra(KEY_READ, false)

        Log.i(SecondActivity::class.toString(), "Loading image content in second activity")

        Glide.with(this)
            .load(CAT_IMAGE_LINK)
            .into(binding.myImageView)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        onBackPressedDispatcher.addCallback(this) {
            Log.i(SecondActivity::class.toString(), "Read switch pressed")
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, binding.readSwitch.isChecked) })
            finish()
        }
    }

    companion object {
        const val KEY_READ = "readSecondArticle"
    }
}
