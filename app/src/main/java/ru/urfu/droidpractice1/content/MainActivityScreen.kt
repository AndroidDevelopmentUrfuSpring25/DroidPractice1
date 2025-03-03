@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.util.Log

import android.content.Context
import android.content.Intent

import androidx.compose.ui.graphics.Color
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ArticleLink
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.SecondActivity.Companion.keyRead
import ru.urfu.droidpractice1.models.RatingModel
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    isRead: Boolean,
    launcherResult: ActivityResultLauncher<Intent>
) {
    val titleSize = 32
    val textSize = 18
    val imageLink = "https://avatars.mds.yandex.net/get-entity_search/5512046/953703644/S600xU_2x"

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
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp, start = 10.dp, bottom = 15.dp, end = 10.dp)
                        .verticalScroll(rememberScrollState())
                ){
                    Text(
                        modifier = Modifier
                            .padding(bottom = 15.dp),

                        text = stringResource(R.string.first_article_title),
                        fontSize = titleSize.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        lineHeight = (titleSize*1.2).sp

                    )

                    LikeDislikeStatistic()

                    AsyncImage(
                        model = imageLink,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .fillMaxWidth()
                            .fillMaxSize()

                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp),

                        text = stringResource(R.string.first_article_text1),
                        fontSize = textSize.sp,
                        lineHeight = (textSize*1.2).sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 0.dp,
                                bottom = 10.dp),

                        text = stringResource(R.string.first_article_text2),
                        fontSize = textSize.sp,
                        lineHeight = (textSize*1.2).sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(
                                top = 0.dp,
                                start = 10.dp,
                                end = 10.dp,
                                bottom = 10.dp),

                        text = stringResource(R.string.first_article_text3),
                        fontSize = textSize.sp,
                        lineHeight = (textSize*1.2).sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(10.dp),

                        text = stringResource(R.string.first_article_text4),
                        fontSize = 14.sp,
                        lineHeight = (14*1.2).sp,

                        fontStyle = FontStyle.Italic
                    )

                    val context = LocalContext.current

                    Card(
                        modifier = Modifier.clickable {
                            val intent = Intent(context, SecondActivity::class.java).apply {
                                putExtra(keyRead, isRead)
                            }
                            Log.d(SecondActivity::class.toString(), "Starting second activity")
                            launcherResult.launch(intent)
                        }
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.second_article_title
                            ),
                            modifier = Modifier.padding(14.dp),
                            color = if (isRead) Color.Gray else Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShareButton() {
    val context = LocalContext.current
    val title = stringResource(R.string.first_article_title)

    Button(
        onClick = { shareArticle(context, "$title \n\n$ArticleLink") },
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        modifier = Modifier.padding(18.dp),

    ) {
        Text(text = "Поделиться")
    }
}

private fun shareArticle(context: Context, text: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(shareIntent, null))
}

@Preview(showBackground = true)
@Composable
fun LikeDislikeStatistic() {
    val statistic: RatingModel = viewModel()

    val size = 32

    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.clickable { statistic.pressLike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "Like",
                tint = if (statistic.isLiked.value) Color.Black else Color.Gray,
                modifier = Modifier.size(size.dp)
            )

            Text(
                modifier = Modifier.padding(top = 6.dp, start = 8.dp),
                text = statistic.likeIconCounter.intValue.toString(),
                fontSize = size.sp
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { statistic.pressDislike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dislike),
                contentDescription = "Dislike",

                tint = if (statistic.isDisliked.value) Color.Black else Color.Gray,

                modifier = Modifier
                    .size(size.dp)
            )

            Text(
                modifier = Modifier.padding(top = 6.dp, start = 8.dp),
                text = statistic.dislikeIconCounter.intValue.toString(),
                fontSize = size.sp
            )
        }
    }
}

