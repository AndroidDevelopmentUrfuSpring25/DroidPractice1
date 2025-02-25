package ru.urfu.droidpractice1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar geri tuşu
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Makale içeriğini ayarla
        binding.articleText.text = """
            ⚽«Ливерпуль» — чемпион, это понятно. Но как так вышло?
            
Похоже, «Ливерпуль» забирает чемпионство. В этом нет сомнений после уверенной победы над «Сити» в Манчестере. Лидер АПЛ оторвался от «Арсенала» на восемь очков по потерянным и на 11 по набранным. «Ливерпулю» осталось сыграть в этом сезоне 11 матчей, из которых семь — на своём поле (в том числе с «Арсеналом»). Математически команда Арне Слота может позволить себе несколько поражений в этом чемпионате. До сих пор было только одно — в сентябре от «Ноттингем Форест» (0:1), который выиграл во многом вопреки происходящему на поле.

По состоянию на сегодня Opta оценивает вероятность чемпионства «Ливерпуля» в 95,8%. Одна из главных букмекерских компаний Великобритании Paddy Power уже рассчиталась с клиентами, которые ставили на титул команды Слота, как если бы ставка уже зашла. Очевидно, что это вопрос времени. Разве что какой-то катаклизм может оставить «Ливерпуль» без первого места.

Но как мы оказались в этой точке?
        """.trimIndent()

        // Okundu butonu çalışıyor
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        binding.okunduButton.setOnClickListener {
            with(prefs.edit()) {
                putBoolean("okundu", true)
                apply()
            }
            Toast.makeText(this, "Makale okundu olarak işaretlendi!", Toast.LENGTH_SHORT).show()
            finish() // Geri dön
        }
    }
}
