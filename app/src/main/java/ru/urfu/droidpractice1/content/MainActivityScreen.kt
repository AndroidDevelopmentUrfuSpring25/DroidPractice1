@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen() {
    val sharedPreferences = RememberPreferences()
    var isSecondArticleRead by remember { mutableStateOf(sharedPreferences.getBoolean("is_second_article_read", false)) }

    LaunchedEffect(Unit) {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            if (key == "is_second_article_read") {
                isSecondArticleRead = sharedPreferences.getBoolean(key, false)
            }
        }
    }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                ArticleContent(isSecondArticleRead)
            }
        }
    }
}

@Composable
fun ArticleContent(isSecondArticleRead: Boolean) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(id = R.string.article1_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.article1_p1),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = rememberImagePainter(data = "https://img.championat.com/s/732x488/news/big/r/i/hokkejnyj-klub-ska-v-2024-go.jpg"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.article1_p2),
            style = MaterialTheme.typography.bodyMedium
        )

        ShareButton()
        LikeDislikeCounter()
        NavigateToSecondArticleButton(isSecondArticleRead)
    }
}

@Composable
fun ShareButton() {
    val context = LocalContext.current
    Button(onClick = {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Хоккейный клуб СКА в 2024 году потратил на зарплаты почти 5 млрд рублей")
        }
        context.startActivity(Intent.createChooser(shareIntent, "Поделиться статьёй"))
    }) {
        Text(stringResource(id = R.string.article1_share))
    }
}

@Composable
fun LikeDislikeCounter() {
    val sharedPreferences = RememberPreferences()
    var likes by rememberSaveable { mutableStateOf(sharedPreferences.getInt("likes", 0)) }
    var dislikes by rememberSaveable { mutableStateOf(sharedPreferences.getInt("dislikes", 0)) }
    var isLiked by rememberSaveable { mutableStateOf(sharedPreferences.getBoolean("is_liked", false)) }
    var isDisliked by rememberSaveable { mutableStateOf(sharedPreferences.getBoolean("is_disliked", false)) }

    Row {
        Button(
            onClick = {
                if (!isLiked && !isDisliked) {
                    likes++
                    isLiked = true
                    sharedPreferences.edit().apply {
                        putInt("likes", likes)
                        putBoolean("is_liked", true)
                        apply()
                    }
                }
            },
            enabled = !isLiked && !isDisliked
        ) {
            Text("Лайк: $likes")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                if (!isLiked && !isDisliked) {
                    dislikes++
                    isDisliked = true
                    sharedPreferences.edit().apply {
                        putInt("dislikes", dislikes)
                        putBoolean("is_disliked", true)
                        apply()
                    }
                }
            },
            enabled = !isLiked && !isDisliked
        ) {
            Text("Дизлайк: $dislikes")
        }
    }
}

@Composable
fun RememberPreferences(): SharedPreferences {
    val context = LocalContext.current
    return remember { context.getSharedPreferences("likes_dislikes", Context.MODE_PRIVATE) }
}


@Composable
fun NavigateToSecondArticleButton(isSecondArticleRead: Boolean) {
    val context = LocalContext.current
    Column {
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("Перейти ко второй статье")
        }
        if (isSecondArticleRead) {
            Text(
                text = "Статья прочитана",
                color = Color.Green,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}
