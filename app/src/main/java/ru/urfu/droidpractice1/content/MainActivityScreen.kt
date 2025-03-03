@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme


@Composable
fun LikeDislikeSectionSimple() {
    var likesCount by rememberSaveable { mutableIntStateOf(0) }
    var dislikesCount by rememberSaveable { mutableIntStateOf(0) }
    var voteState by rememberSaveable { mutableStateOf("none") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
        ,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                when (voteState) {
                    "like" -> {
                        voteState = "none"
                        likesCount--
                    }
                    "dislike" -> {
                        voteState = "like"
                        dislikesCount--
                        likesCount++
                    }
                    "none" -> {
                        voteState = "like"
                        likesCount++
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3CB371),
                contentColor = Color.White,
            ),
        ) {
            Text(
                text = "😎 $likesCount",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Button(
            onClick = {
                when (voteState) {
                    "dislike" -> {
                        voteState = "none"
                        dislikesCount--
                    }
                    "like" -> {
                        voteState = "dislike"
                        likesCount--
                        dislikesCount++
                    }
                    "none" -> {
                        voteState = "dislike"
                        dislikesCount++
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFA8072),
                contentColor = Color.White,
            ),
        ) {
            Text(
                text = "💩 $dislikesCount",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun NavigationButton(isRead: Boolean) {
    val context = LocalContext.current
    val buttonTextColor = if (isRead) Color.LightGray else Color.White

    Button(
        onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = buttonTextColor
        ),
        modifier = Modifier
            .fillMaxSize()
            .height(70.dp)
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = "Первые приложения на Swift",
            fontSize = 20.sp,
        )

    }
}


@Composable
fun MainActivityScreen(isRead: Boolean) {

    val articleParts = listOf(
        "Swift — это современный язык программирования, разработанный Apple для создания приложений под iOS, macOS, watchOS и tvOS. Он сочетает в себе высокую производительность и удобный синтаксис, что делает разработку более эффективной. Благодаря открытой лицензии Swift используется не только в экосистеме Apple, но и в серверных и кроссплатформенных решениях.",
        "Одним из ключевых преимуществ Swift является безопасность. Язык поддерживает строгую типизацию, управление памятью через ARC и систему опциональных значений, что помогает избежать множества ошибок.",
        "Производительность Swift сравнима с языками низкого уровня, такими как C++, благодаря оптимизированному компилятору и современным подходам к управлению ресурсами. Он поддерживает параллельные вычисления, что особенно важно для многопоточных приложений.",
        "Сообщество Swift активно развивается, а сам язык получает регулярные обновления. Его гибкость и мощные инструменты делают его востребованным в индустрии, от мобильной разработки до серверных решений и машинного обучения."
    )
    val context = LocalContext.current

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(Color(255, 160, 122)),
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                            modifier = Modifier.padding(15.dp),
                            fontSize = 24.sp
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            val shareText = articleParts.joinToString(separator = "\n")
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }) {
                            AsyncImage(
                                model = "https://www.pngrepo.com/png/66766/512/share.png",
                                contentDescription = "Поделиться",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "SWIFT",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    textAlign = TextAlign.Center,
                )
                articleParts.firstOrNull()?.let { firstPar ->
                    Text(
                        modifier = Modifier
                            .padding(20.dp)
                        ,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        text = firstPar
                    )
                }

                AsyncImage(
                    model = "https://www.apple.com/newsroom/images/2024/10/" +
                            "apples-swift-student-challenge-to-open-in-february-2025/article" +
                            "/Apple-Swift-Student-Challenge-logo_quick-read-16x9.jpg.large.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(250.dp)
                        .padding(10.dp)
//                        .size(250.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(20.dp))

                )
                articleParts.getOrNull(1)?.let { secondPar ->
                    Text(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 20.dp)
                        ,

                        fontSize = 17.sp,
                        color = Color.White,
                        text = secondPar
                    )
                }

                articleParts.getOrNull(2)?.let { thirdPar ->
                    Text(
                        modifier = Modifier
                            .padding(20.dp)
                        ,
                        fontSize = 17.sp,
                        color = Color.Black,
                        text = thirdPar
                    )
                }

                articleParts.getOrNull(3)?.let { fourthPar ->
                    Text(
                        modifier = Modifier
                            .padding(20.dp)
                        ,
                        fontSize = 17.sp,
                        color = Color.Black,
                        text = fourthPar
                    )
                }

                LikeDislikeSectionSimple()

                NavigationButton(isRead = isRead)
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainActivityScreen(isRead = isRead)
//}