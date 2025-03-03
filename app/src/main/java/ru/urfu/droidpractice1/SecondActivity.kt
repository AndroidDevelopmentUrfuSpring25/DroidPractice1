package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.ComponentActivity
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var readSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val wwdcImage = findViewById<ImageView>(R.id.article2_photo_wwdc)
        wwdcImage.load("https://cdn.arstechnica.net/wp-content/uploads/2009/06/WWDCbanner_flickr.jpg")

        val linkedinImage = findViewById<ImageView>(R.id.article2_photo_linkedin)
        linkedinImage.load("https://avatars.mds.yandex.net/i?id=9a524b44f99631b95ebeb5f91fe76df7fe1e6128-5210205-images-thumbs&n=13")

        val yahooImage = findViewById<ImageView>(R.id.article2_photo_yahoo)
        yahooImage.load("https://avatars.mds.yandex.net/i?id=9b95ed72deb9dbb910de4e3387f427b12a2d3d1a-5916763-images-thumbs&n=13")

        readSwitch = binding.readSwitch

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        val isRead = sharedPreferences.getBoolean("isRead", false)
        readSwitch.isChecked = isRead

        readSwitch.setOnCheckedChangeListener { _, checked ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("isRead", checked)
            editor.apply()
        }

    }
}