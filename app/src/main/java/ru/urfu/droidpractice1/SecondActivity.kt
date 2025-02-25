package ru.urfu.droidpractice1

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.articleContent.text = """
            Этот перформанс Килиана будут вспоминать ещё долго.
В ярчайшем первом квалификационном матче плей-офф Лиги чемпионов «Реал» к 86-й минуте уступал «Манчестер Сити» 1:2 и классически всё перевернул, забив ещё дважды. В Мадриде же чемпионы Испании устроили настоящую казнь.После травмы в основу мадридцев возвратился Рюдигер, чтобы Тчуамени вернулся в опорную зону. Антонио наверняка было грустно, ведь в запасе «Сити» остался Холанд — в прошлом году немец успешно нейтрализовал норвежца. Но если Эрлинг хотя бы попал в заявку (вместе с Де Брёйне и Грилишем), то Аканджи из-за травмы встречу пропустил. На правом краю обороны вышел Хусанов. Причём узбекский защитник вообще мог не приехать из-за проблем с визой. Чемпионы Англии отправили его чартерным рейсом через Грецию — план удался.

«Реал» быстро продолжил дело концовки матча в Манчестере. И гол-то получился простейшим. Уже на четвёртой минуте Асенсио ювелирно забросил вперёд, а Мбаппе чётко разобрался с офсайдной линией, вынырнул из-за спины Диаша и спокойно перекинул выдвинувшегося вперёд Эдерсона.

Увы, вскоре после этого Аке пришлось менять Стоунза — англичанин пострадал в единоборстве с Винисиусом. Мало было проблем у «Манчестера», так прибавилась ещё одна.
        """.trimIndent()


        binding.readSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Вы прочитали статью", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Статья не прочитана", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
