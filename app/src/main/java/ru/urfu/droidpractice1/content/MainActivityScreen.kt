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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.view.RatingViewModel

private const val ARTICLE_LINK = "https://cats.fandom.com/ru/wiki/%D0%9A%D0%BE%D1%88%D0%BA%D0%B0"

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
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.first_article_title,
                        ),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(
                                stringResource(
                                    id = R.string.first_article_text_intro
                                )
                            )
                        },
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/catspedia/images/d/da/Back_white_cats.jpg/revision/latest?cb=20121231121515&path-prefix=ru",
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth()
                            .fillMaxSize()
                            .size(250.dp)
                    )
                    Text(
                        text = stringResource(
                            id = R.string.first_article_text_main
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    RatingStatistic()
                    val context = LocalContext.current
                    Card(
                        modifier = Modifier.clickable {
                            val intent = Intent(context, SecondActivity::class.java).apply {
                                putExtra(KEY_READ, read)
                            }
                            Log.d(SecondActivity::class.toString(), "Starting second activity")
                            resultLauncher.launch(intent)
                        }
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.second_article_title
                            ),
                            modifier = Modifier.padding(14.dp),
                            color = if (read) Color.Gray else Color.Black
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

    Button(
        onClick = { shareContent(context, ARTICLE_LINK) },
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
    ) {
        Text(text = "Поделиться")
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStatistic() {
    val statistic: RatingViewModel = viewModel()

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.clickable { statistic.pressLike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like_icon),
                contentDescription = "Понравилось",
                tint = if (statistic.isLiked.value) Color.Green else Color.Gray,
                modifier = Modifier.size(20.dp)
            )

            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = statistic.likesCount.intValue.toString(),
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { statistic.pressDislike() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dislike_icon),
                contentDescription = "Не понравилось",
                tint = if (statistic.isDisliked.value) Color.Red else Color.Gray,
                modifier = Modifier.size(20.dp)
            )

            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = statistic.dislikesCount.intValue.toString(),
                fontSize = 20.sp
            )
        }
    }
}

private fun shareContent(context: Context, text: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(shareIntent, null))
}
