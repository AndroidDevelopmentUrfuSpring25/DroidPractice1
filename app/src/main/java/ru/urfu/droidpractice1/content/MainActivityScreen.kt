@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainActivity
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

private const val SHARE_TEXT = "Поделиться"
private const val LINK_TO_ARTICLE = "https://habr.com/ru/articles/868784/"
const val LESSON_LOG = "LESSON"

@Composable
fun MainActivityScreen(
    read: Boolean,
    resultLauncher: ActivityResultLauncher<Intent>
) {

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        ShareButton()
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(id = R.string.first_article_title),
                    style = Typography.bodyLarge,
                )
                Text(
                    text = stringResource(id = R.string.first_article_text_intro),
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = stringResource(id = R.string.first_article_case),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
                AsyncImage(
                    model = "https://upload.wikimedia.org/wikipedia/commons/e/e8/New-logo-magnit.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp)
                        .fillMaxWidth()
                        .size(200.dp)
                )
                Text(
                    text = stringResource(id = R.string.first_article_case_text),
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = stringResource(id = R.string.first_article_model_experiments),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
                BulletPointLlmStatsList()
                Text(
                    text = stringResource(id = R.string.first_article_pre_conclusion),
                    modifier = Modifier.padding(top = 12.dp),
                    style = Typography.bodyMedium
                )
                Text(
                    text = stringResource(id = R.string.first_article_conclusion),
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = stringResource(id = R.string.first_article_conclusion_text),
                    modifier = Modifier.padding(top = 12.dp),
                    style = Typography.bodyMedium
                )
                LikesAndDislikesStats()
                val context = LocalContext.current
                Card(
                    modifier = Modifier.clickable {
                        val intent = Intent(context, SecondActivity::class.java)
                        Log.d(LESSON_LOG, "Starting second activity")
                        resultLauncher.launch(intent)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.second_article_title),
                        modifier = Modifier.padding(12.dp),
                        color = if (read) Color.Gray else Color.Black,
                        style = Typography.bodyMedium
                    )
                }
            }
        }
    }
}




@Composable
fun BulletPointLlmStatsList() {
    val items = listOf(
        R.string.first_article_model_chatgpt,
        R.string.first_article_model_yandexgpt
    )

    items.forEach { itemId ->
        Text(
            text = stringResource(id = R.string.item_text, stringResource(id = itemId)),
            modifier = Modifier.padding(top = 12.dp, start = 12.dp),
            style = Typography.bodyMedium,
        )
    }
}



@Composable
fun ShareButton() {
    val context = LocalContext.current

    IconButton(
        onClick = { shareContent(context, LINK_TO_ARTICLE) },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = SHARE_TEXT,
            tint = Color(0xFFBE0026)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LikesAndDislikesStats() {
    val viewModel: LikesDislikesViewModel = viewModel()

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.clickable { viewModel.toggleLike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like_icon),
                contentDescription = "Like",
                tint = viewModel.state.value.likeColor,
                modifier = Modifier.size(24.dp)
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = viewModel.state.value.likeCount.toString(),
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { viewModel.toggleDislike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dislike_icon),
                contentDescription = "Dislike",
                tint = viewModel.state.value.dislikeColor,
                modifier = Modifier.size(24.dp)
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = viewModel.state.value.dislikeCount.toString(),
                fontSize = 20.sp
            )
        }
    }
}


fun shareContent(context: Context, text: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(shareIntent, null))
}