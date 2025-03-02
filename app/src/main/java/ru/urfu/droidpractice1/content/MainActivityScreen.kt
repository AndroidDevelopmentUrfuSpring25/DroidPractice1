package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainActivityScreen(
    isRead: Boolean = false,
    likesCount: Int = 0,
    dislikesCount: Int = 0,
    isLiked: Boolean = false,
    isDisliked: Boolean = false,
    onClickLike: () -> Unit = {},
    onClickDisLike: () -> Unit = {},
    onClickOtherArticle: () -> Unit = {},
    onClickShare: () -> Unit = {}
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                        )
                    },
                    actions = {
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    onClickShare()
                                }
                                .padding(end = 15.dp),
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Text(
                    text = stringResource(R.string.article_1_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )

                Text(
                    text = stringResource(R.string.article_1_intro),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    fontSize = 15.sp
                )

                AsyncImage(
                    model = "https://trashbox.ru/ifiles/1870159_aaa642_logo1.webp.png-orig/intel-predstavila-processory-14-go-pokoleniya-cena-i-arhitektura-prezhnie-no-proizvoditelnost-vyshe-6.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                )

                Text(
                    text = stringResource(R.string.article_1_content),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = stringResource(R.string.article_1_conclusion),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    fontSize = 15.sp
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onClickLike()
                            }
                            .padding(end = 15.dp),
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = null,
                        tint = if (isLiked) Color.Green else Color.Black
                    )
                    Text(
                        text = "$likesCount",
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onClickDisLike()
                            }
                            .padding(end = 15.dp),
                        painter = painterResource(id = R.drawable.ic_dislike),
                        contentDescription = null,
                        tint = if (isDisliked) Color.Red else Color.Black
                    )
                    Text(
                        text = "$dislikesCount",
                    )
                }

                Card(
                    modifier = Modifier
                        .clickable {
                            onClickOtherArticle()
                        }
                        .padding(top = 15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.article_2_title),
                        modifier = Modifier.padding(15.dp),
                        color = if (isRead) Color.Gray else Color.Black,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}