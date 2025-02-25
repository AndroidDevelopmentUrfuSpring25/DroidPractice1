@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.foundation.Image
import ru.urfu.droidpractice1.SecondActivity

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current
    var likeCount by remember { mutableStateOf(0) }
    var dislikeCount by remember { mutableStateOf(0) }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Makale 1") }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Makale Başlığı
                Text(
                    text = "Хабиб, Ислам и даже Фёдор. Как звёзды ММА проигрывали в самбо",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                val imagePainter = painterResource(id = R.drawable.khabib_image) // Drawable'dan yükleme
                Image(
                    painter = imagePainter,
                    contentDescription = "Article Image",
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )
                Text(text = "Buraya resim gelecek", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                // Makale İçeriği
                Text(
                    text = "А Мераб Двалишвили «наполучал» от российского самбиста в финале чемпионата мира.\n" +
                            "С 5 по 7 марта в Краснодаре пройдёт чемпионат России по спортивному и боевому самбо. Это главный национальный турнир сезона, по итогам которого будут сформированы сборные России для участия в чемпионатах Европы и мира.\n"
                            ,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Like ve Dislike Butonları
                Row {
                    Button(
                        onClick = { likeCount++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text("👍 $likeCount")
                    }

                    Button(
                        onClick = { dislikeCount++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text("👎 $dislikeCount")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Paylaş Butonu
                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "Bu makaleyi oku: Teknolojinin Önemi!")
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Paylaş"))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Paylaş", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    context.startActivity(Intent(context, SecondActivity::class.java))
                }) {
                    Text(text = "Перейти ко второй статье")
            }
        }
    }
    }
}