@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainActivityScreen(
    upVoteCount: Int = 0,
    downVoteCount: Int = 0,
    isRead: Boolean = false,
    upVote: () -> Unit = {},
    downVote: () -> Unit = {},
    onToOtherScreenClicked: () -> Unit = {},
) {
    val context = LocalContext.current
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
                        Icon(
                            modifier = Modifier
                                .clickable { shareArticle(context) }
                                .width(30.dp)
                                .height(30.dp)
                                .padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.share_icon),
                            tint = Color.Black,
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(10.dp)
            ) {


                Text(
                    text = stringResource(R.string.article_title2),
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    style = Typography.titleLarge
                )

                AsyncImage(
                    model = "https://static.tildacdn.com/tild6261-6636-4330-a563-303835316538/1644983725_13-klubla.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                Text(
                    text = "   " + stringResource(R.string.paragraph_1),
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )

                Text(
                    text = "   " + stringResource(R.string.paragraph_2),
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )

                Text(
                    text = "   " + stringResource(R.string.paragraph_3),
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )

                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                upVote()
                            }
                            .width(50.dp)
                            .height(50.dp)
                            .padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.thumb_up),
                        tint = Color.Black,
                        contentDescription = null
                    )
                    Text(
                        text = "$upVoteCount",
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(end = 20.dp) // Отступ между текстом и иконкой
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                downVote()
                            }
                            .width(50.dp)
                            .height(50.dp)
                            .padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.thumb_down),
                        tint = Color.Black,
                        contentDescription = null
                    )
                    Text(
                        text = "$downVoteCount",
                        style = Typography.bodyLarge
                    )
                }

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Внимание!!! Врачи врут? Так ли вредны блины на самом деле, как пишут врачи?",
                        modifier = Modifier.padding(16.dp),
                        color = if (isRead) Color.Gray else Color.Black
                    )
                }
            }
        }
    }
}


private fun shareArticle(context: Context) {
    val shareText = "Привет посмотри статью https://www.rbc.ru/life/news/65efe12c9a7947a5a9ef8566"
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, null))
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}