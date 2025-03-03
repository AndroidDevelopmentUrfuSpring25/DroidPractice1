package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.request.ErrorResult
import coil.request.SuccessResult

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_second)

            val backButton = findViewById<Button>(R.id.backButton)
            val titleTextView = findViewById<TextView>(R.id.titleTextView)
            val imageView = findViewById<ImageView>(R.id.articleImageView)
            val subtitleTextView = findViewById<TextView>(R.id.subtitleTextView)
            val contentTextView = findViewById<TextView>(R.id.contentTextView)
            val readCheckBox = findViewById<CheckBox>(R.id.readCheckBox)

            backButton.setOnClickListener {
                val resultIntent = Intent().apply {
                    putExtra("isRead", isRead)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }

            titleTextView.text = "Дюрант может уйти. Но найдётся ли команда, готовая рискнуть?"
            subtitleTextView.text = "Если у вас есть Кевин, вы должны побеждать. В «Финиксе» явно это не происходит."
            
            try {
                imageView.load("https://img.championat.com/s/732x488/news/big/x/h/kevin-dyurant-dyurant-mozhet-ujti_17410204201131156592.jpg") {
                    listener(
                        onSuccess = { request, result ->
                            Log.d(TAG, "Image loaded successfully")
                        },
                        onError = { request, result ->
                            Log.e(TAG, "Error loading image: ${(result as ErrorResult).throwable.message}")
                        }
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading image", e)
            }
            
            contentTextView.text = "Если «Финикс» пролетит мимо плей-офф, предстоящее лето может стать началом большого передела. И первым на выход, скорее всего, будет Кевин Дюрант. Уже зимой «Санз» подумывали о его обмене, но, если этот сезон закончится провалом, клуб вернётся к этой идее всерьёз.\n\n" +
                    "Сейчас команда болтается на 11-м месте Запада с балансом 28-32, и, чтобы зацепиться хотя бы за плей-ин, нужно отыграть 3,5 победы. Новый тренер Майк Буденхольцер пока явно не справляется: лидеры не «сцепились», а слабая скамейка только добавляет проблем. Ставка на три звезды — Дюранта, Букера и Била — пока выглядит провальной. Времени исправить ситуацию остаётся катастрофически мало.\n\n" +
                    "Сам Дюрант играет круто: 26,7 очка, шесть подборов и 4,3 передачи за матч. Но ему уже 36, а контракт истекает в 2025 году. При его зарплате в $ 54,7 млн «Санз» не смогут просто так отправить его куда угодно. Кевин должен будет дать добро, и вряд ли он согласится продлить контракт с командой, которая ему не по душе. Поэтому летом «Финикс» и Дюрант, скорее всего, начнут вместе искать ему новый дом, по данным Шэмса Чарании."

            readCheckBox.setOnCheckedChangeListener { _, isChecked ->
                isRead = isChecked
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
            finish()
        }
    }
}