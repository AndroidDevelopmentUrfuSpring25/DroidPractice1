@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

private const val ARTICLE_LINK = "https://cats.fandom.com/ru/wiki/%D0%9A%D0%BE%D1%88%D0%BA%D0%B0"

@Composable
fun MainActivityScreen(
    read: Boolean,
    rating: Int,
    resultLauncher: ActivityResultLauncher<Intent>,
    onClickLike: () -> Unit,
    onClickDislike: () -> Unit
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
                        fontSize = Typography.titleLarge.fontSize,
                        fontWeight = Typography.titleLarge.fontWeight
                    )
                    Text(
                        text = stringResource(
                            id = R.string.first_article_text_intro
                        ),
                        fontSize = Typography.bodyLarge.fontSize,
                        fontWeight = Typography.bodyLarge.fontWeight,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/catspedia/images/d/da/Back_white_cats.jpg/revision/latest?cb=20121231121515&path-prefix=ru",
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxSize()
                    )
                    Text(
                        text = stringResource(
                            id = R.string.first_article_text_main
                        ),
                        fontSize = Typography.bodyMedium.fontSize,
                        fontWeight = Typography.bodyMedium.fontWeight,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    RatingStatistic(rating, onClickLike, onClickDislike)
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

@Composable
fun RatingStatistic(rating: Int, onClickLike: () -> Unit, onClickDislike: () -> Unit) {
    val iconSize = 30.dp
    val iconPadding = 10.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Icon(
            modifier = Modifier
                .clickable {
                    onClickLike()
                }
                .padding(iconPadding)
                .size(iconSize),
            painter = painterResource(id = R.drawable.like_icon),
            contentDescription = null,
        )
        Text(
            text = "$rating"
        )
        Icon(
            modifier = Modifier
                .clickable {
                    onClickDislike()
                }
                .padding(iconPadding)
                .size(iconSize),
            painter = painterResource(id = R.drawable.dislike_icon),
            contentDescription = null,
        )
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
