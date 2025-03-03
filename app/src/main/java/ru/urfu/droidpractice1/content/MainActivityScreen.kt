@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(articleRead: Boolean) {
    val context = LocalContext.current

    var likesCount by rememberSaveable { mutableIntStateOf(0) }
    var dislikesCount by rememberSaveable { mutableIntStateOf(0) }

    val articleTitle = "Матч Атлетико: Победа с минимальным счётом"
    val articleContent = """
        «Атлетико» Мадрид принимал «Атлетик» Бильбао в матче 26-го тура испанской Ла Лиги. Команды играли на стадионе «Сивитас Метрополитано» в Мадриде (Испания). Встречу обслуживала судейская бригада под руководством Хесуса Хиль Мансано. Игра завершилась победой столичного клуба с минимальным счётом — 1:0.

        Счёт в матче был открыт на 66-й минуте. Аргентинский форвард Хулиан Альварес отличился с передачи полузащитника Маркоса Льоренте. Забитый в середине второго тайма мяч так и остался единственным в игре.

        Победа позволила «Атлетико» возглавить турнирную таблицу чемпионата Испании с 56 очками в 26 матчах. Отрыв от мадридского «Реала» теперь составляет два очка. «Барселону» подопечные Диего Симеоне также опережают на два очка, но у каталонцев остаётся матч в запасе. «Атлетик» Бильбао находится на четвёртой строчке, заработав 48 очков.
    """.trimIndent()

    DroidPractice1Theme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Статья") })
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = articleTitle, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (articleRead) "Вторая статья прочитана" else "Вторая статья не прочитана",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (articleRead) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(R.drawable.atleti),
                    contentDescription = "Изображение статьи",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = articleContent, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { likesCount++ }) {
                            Text("Like")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Likes: $likesCount")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { dislikesCount++ }) {
                            Text("Dislike")
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Dislikes: $dislikesCount")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "$articleTitle\n\n$articleContent")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Поделиться статьей через"))
                }) {
                    Text("Поделиться статьей")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    context.startActivity(Intent(context, SecondActivity::class.java))
                }) {
                    Text("Перейти ко второй статье")
                }
            }
        }
    }
}
