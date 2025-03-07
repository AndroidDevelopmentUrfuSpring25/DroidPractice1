@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import ru.urfu.droidpractice1.viewmodel.ArticleViewModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Purple40
import ru.urfu.droidpractice1.ui.theme.Purple80
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun ArticleScreen() {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("SwitchPrefs", Context.MODE_PRIVATE)
    }
    var isRead by remember { mutableStateOf(sharedPrefs.getBoolean("isRead", false)) }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isRead = result.data?.getBooleanExtra("isRead", false) ?: false
        }
    }

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.article1_topbar),
                        style = Typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },

                actions = {
                    IconButton(
                        onClick = {
                            val articleText = context.getString(R.string.article_text)
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, articleText)
                            }

                            context.startActivity(
                                Intent.createChooser(
                                    shareIntent, "Поделиться через"
                                )
                            )
                        }) {
                        Icon(Icons.Default.Share, contentDescription = "Поделиться")
                    }
                },

                modifier = Modifier.wrapContentHeight()

            )
        }, bottomBar = {
            Button(
                onClick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    resultLauncher.launch(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isRead) Purple80 else Purple40)
            ) {
                Text(text = "Следующая статья")
            }
        }) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.article_title),
                        style = Typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    AsyncImage(
                        model = "https://img.championat.com/s/732x488/news/big/l/s/kak-getzhi-stal-samym-yarkim-bojcom-lyogkogo-vesa_17410047411063813390.jpg",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Джастин отдал борьбе 16 лет, а теперь коллекционирует страшные нокауты.",
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Джастин Гэтжи — идеальный пример случая, когда прозвище бойца максимально ему подходит. Хайлайт — можно ли придумать что-то лучше для человека, у которого абсолютно все поединки в UFC оборачиваются шоу? 13 бoёв — 13 бoнусов, трижды поединок с участием Гэтжи становился «Бoем гoда». Джастин даже проигрывает зрелищно. И, скорее всего, утверждение, что Гэтжи — самый яркий боец лёгкого веса, не станет преувеличением. Включая его бои, вы знаете, чего ждать. Так как базовый борец пришёл к этому?",
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Likes(ArticleViewModel(context))
                }
            }
        }
    }
}

@Composable
fun Likes(viewModel: ArticleViewModel) {
    val likes = viewModel.likes.collectAsState()
    val dislikes = viewModel.dislikes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Лайки: ${likes.value}")
        Text(text = "Дизлайки: ${dislikes.value}")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = { viewModel.like() }) {
                Icon(
                    imageVector = Icons.Filled.SentimentVerySatisfied,
                    contentDescription = "Лайк",
                    Modifier.scale(2f)
                )
            }
            IconButton(onClick = { viewModel.dislike() }) {
                Icon(
                    imageVector = Icons.Filled.SentimentVeryDissatisfied,
                    contentDescription = "Дизлайк",
                    Modifier.scale(2f)
                )
            }
        }
    }
}
