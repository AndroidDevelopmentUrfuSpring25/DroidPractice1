@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }
}

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current

    var likeCount by rememberSaveable { mutableStateOf(0) }
    var dislikeCount by rememberSaveable { mutableStateOf(0) }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("MainActivity Title") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF6200EE))
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Почему Италия опозорилась в Лиге чемпионов? Причина не в слабости Серии А",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF6200EE)
                )

                Spacer(modifier = Modifier.height(16.dp))

                val imagePainter = painterResource(id = R.drawable.soccer_image)
                Image(
                    painter = imagePainter,
                    contentDescription = "Article Image",
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )



                SelectionContainer {
                    Text(
                        text = """
                         Реальная катастрофа только у «Милана». «Ювентус» и «Аталанта» – совсем другие истории.
Все счастливые друг на друга похожи, а каждая несчастливая несчастлива по-своему. В данном случае речь не о семьях, как было у Льва Николаевича Толстого, а о командах-участницах первого раунда плей-офф Лиги чемпионов. Феноменальное несчастье постигло итальянцев: турнир за два дня покинули «Милан», «Аталанта» и «Ювентус».

Разумеется, сразу пошли разговоры о крахе Серии А. Игроки, мол, там слабые, лига мусорная, чемпионаты Нидерландов и даже Бельгии сильнее. И вообще, как итальянцы посмели до такого докатиться?! По-другому и быть не могло. Когда разом вылетают три команды, да ещё и на первой же стадии плей-офф, да ещё и от соперников не из ведущих лиг, такие разговоры неизбежны.

Однако не нужно забывать, что любое обобщение вредит. Каждый пример нужно рассматривать отдельно. Каждая итальянская команда – «Милан», «Аталанта» и «Ювентус» – несчастлива по-своему. И у вылета каждой из них есть свои – в том числе объективные – причины.
                        """,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))




                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { likeCount++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text(text = "👍 $likeCount")
                    }
                    Button(
                        onClick = { dislikeCount++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB00020))
                    ) {
                        Text(text = "👎 $dislikeCount")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Makale metni burada...")
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5))
                ) {
                    Text(text = "Поделиться")
                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(onClick = {
                    context.startActivity(Intent(context, SecondActivity::class.java))
                }) {
                    Text(text = "Перейти ко второй статье")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityScreen() {
    MainActivityScreen()
}
