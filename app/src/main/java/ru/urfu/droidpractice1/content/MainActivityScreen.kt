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
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

private val PaddingSize = 15.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainActivityScreen(
    isRead: Boolean = false,
    rating: Int = 0,
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
                                .padding(end = PaddingSize),
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
                    .padding(PaddingSize)
            ) {
                Text(
                    text = stringResource(R.string.article_1_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingSize),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )

                Text(
                    text = stringResource(R.string.article_1_intro),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingSize),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )

                AsyncImage(
                    model = "https://trashbox.ru/ifiles/1870159_aaa642_logo1.webp.png-orig/intel-predstavila-processory-14-go-pokoleniya-cena-i-arhitektura-prezhnie-no-proizvoditelnost-vyshe-6.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingSize)
                )

                Text(
                    text = stringResource(R.string.article_1_content),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingSize),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )

                Text(
                    text = stringResource(R.string.article_1_conclusion),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingSize),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onClickLike()
                            }
                            .padding(end = PaddingSize),
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = null,
                    )
                    Text(
                        text = "$rating",
                        modifier = Modifier.padding(end = PaddingSize)
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onClickDisLike()
                            }
                            .padding(end = PaddingSize),
                        painter = painterResource(id = R.drawable.ic_dislike),
                        contentDescription = null,
                    )
                }

                Card(
                    modifier = Modifier
                        .clickable {
                            onClickOtherArticle()
                        }
                        .padding(top = PaddingSize)
                ) {
                    Text(
                        text = stringResource(R.string.article_2_title),
                        modifier = Modifier.padding(PaddingSize),
                        color = if (isRead) Color.Gray else Color.Black,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
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