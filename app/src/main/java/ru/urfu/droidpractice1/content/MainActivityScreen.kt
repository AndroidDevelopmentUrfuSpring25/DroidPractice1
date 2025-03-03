@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onToOtherScreenClicked: () -> Unit = {},
    onToShareClicked: () -> Unit = {},
    onLikePlus: () -> Unit = {},
    onLikeMinus: () -> Unit = {},
    read: Boolean = false,
    countLike: Int = 0
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.article_title))
                    },
                    actions = {
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                LikeCounter(
                    onLikePlus = onLikePlus,
                    onLikeMinus = onLikeMinus,
                    countLike = countLike
                )
                Spacer(modifier = Modifier.height(16.dp))
                ArticleContent(
                    onToOtherScreenClicked = onToOtherScreenClicked,
                    read = read
                )
            }
        }
    }
}

@Composable
private fun LikeCounter(
    onLikePlus: () -> Unit,
    onLikeMinus: () -> Unit,
    countLike: Int
) {
    Row {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .clickable { onLikePlus() },
            painter = painterResource(id = R.drawable.like),
            contentDescription = null
        )
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .graphicsLayer { rotationZ = 180f }
                .clickable { onLikeMinus() },
            painter = painterResource(id = R.drawable.like),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = countLike.toString(),
            fontSize = 20.sp
        )
    }
}

@Composable
private fun ArticleContent(
    onToOtherScreenClicked: () -> Unit,
    read: Boolean
) {
    Column {
        Text(
            text = stringResource(id = R.string.title_h1),
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.description_text1),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        AsyncImage(
            model = stringResource(id = R.string.link_picture),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.description_text2),
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToOtherScreenClicked() }
        ) {
            Text(
                text = stringResource(id = R.string.link_card),
                modifier = Modifier.padding(16.dp),
                color = if (read) Color.Gray else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}