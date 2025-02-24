@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    onClickLike: () -> Unit = {},
    onClickDislike: () -> Unit = {},
    onClickShare: () -> Unit = {},
    onClickRecipe: () -> Unit = {},
    cooked: Boolean = false,
    rating: Int = 128
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.clickable { onClickRecipe() },
                    containerColor =
                    if (cooked) BottomAppBarDefaults.containerColor
                    else Color(255, 84, 1),
                    content = {
                        Text(
                            text = stringResource(id = R.string.article_button_recipe),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = if (cooked) Color.Unspecified else Color.White
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = null,
                        modifier = Modifier.clickable { onClickLike() }
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_dislike),
                        contentDescription = null,
                        modifier = Modifier.clickable { onClickDislike() }
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 24.sp
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        modifier = Modifier.clickable { onClickShare() }
                    )
                }
                Text(
                    text = stringResource(id = R.string.article_text),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                )
                GlideImage(
                    model = stringResource(R.string.article_image_1),
                    contentDescription = stringResource(R.string.images_description),
                    contentScale = ContentScale.FillWidth
                )
                GlideImage(
                    model = stringResource(R.string.article_image_2),
                    contentDescription = stringResource(R.string.images_description),
                    contentScale = ContentScale.FillWidth
                )
                GlideImage(
                    model = stringResource(R.string.article_image_3),
                    contentDescription = stringResource(R.string.images_description),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(id = R.string.article_bzu),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.article_link_desc),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}