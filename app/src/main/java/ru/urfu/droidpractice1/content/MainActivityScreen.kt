@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainActivityScreen(
    onClickMore: () -> Unit = {},
    onClickShare: () -> Unit = {},
    onClickLike: () -> Unit = {},
    onClickDislike: () -> Unit = {},
    read: Boolean = false,
    rating: Int = 0
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
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { onClickShare() }
                                .padding(16.dp)
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = ""
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onClickLike() }
                            .padding(horizontal = 8.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_dislike),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onClickDislike() }
                            .padding(horizontal = 8.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 24.sp
                    )
                }
                Text(
                    text = stringResource(R.string.view_article_title_name),
                    style = Typography.titleLarge,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )

                Text(
                    text = stringResource(R.string.article_content_preview),
                    modifier = Modifier
                        .padding(8.dp)
                )
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlideImage(
                        model = stringResource(R.string.image_preview_url),
                        contentDescription = stringResource(R.string.image_description)
                    )
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { onClickMore() }
                    ) {
                        Text(
                            text = stringResource(R.string.read_all),
                            textAlign = TextAlign.Center,
                            color = if (read) Color.Gray else Color.Black,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
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