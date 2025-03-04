@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography
import ru.urfu.droidpractice1.ui.theme.PurpleGrey40
import ru.urfu.droidpractice1.ui.theme.PurpleGrey80
import ru.urfu.droidpractice1.ui.theme.Purple80
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import ru.urfu.droidpractice1.SecondActivity
import coil.compose.AsyncImage

@Composable
fun MainActivityScreen(isSecondArticleRead: Boolean) {
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
                        IconButton(onClick = {
                            shareText(context, "Прочитайте эту статью!")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share"
                            )

                        }
                    }

                )
            }) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)

            ) {
                Column (
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ){
                    Text(
                        text = stringResource(id = R.string.mainHeadingFirstPage),
                        fontSize = Typography.titleLarge.fontSize,
                        lineHeight = Typography.titleLarge.lineHeight,
                        fontFamily = Typography.titleLarge.fontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = Typography.titleLarge.letterSpacing,
                    )


                    LikeDislikeCounter()

                    AsyncImage(
                        model = "https://img.championat.com/s/732x488/news/big/o/o/amur-ne-popal-v-plej-off-khl_1740999268648042281.jpg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp, end = 16.dp, start = 16.dp),
                        contentDescription = "picture",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = stringResource(id = R.string.firstParagraphFirstPage),
                        fontSize = Typography.bodyLarge.fontSize,
                        lineHeight = Typography.bodyLarge.lineHeight,
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = Typography.bodyLarge.letterSpacing,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.secondParagraphFirstPage),
                        fontSize = Typography.bodyLarge.fontSize,
                        lineHeight = Typography.bodyLarge.lineHeight,
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontWeight = Typography.bodyLarge.fontWeight,
                        letterSpacing = Typography.bodyLarge.letterSpacing,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.thirdParagraphFirstPage),
                        fontSize = Typography.bodyLarge.fontSize,
                        lineHeight = Typography.bodyLarge.lineHeight,
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontWeight = Typography.bodyLarge.fontWeight,
                        letterSpacing = Typography.bodyLarge.letterSpacing,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.fourthParagraphFirstPage),
                        fontSize = Typography.bodyLarge.fontSize,
                        lineHeight = Typography.bodyLarge.lineHeight,
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontWeight = Typography.bodyLarge.fontWeight,
                        letterSpacing = Typography.bodyLarge.letterSpacing,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    AsyncImage(
                        model = "https://img.championat.com/i/b/g/17409990851598301748.jpg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 5.dp, end = 16.dp, start = 16.dp),
                        contentDescription = "picture",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = stringResource(id = R.string.fifthParagraphFirstPage),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = PurpleGrey40,
                        lineHeight = 12.sp,
                    )

                    ArticleCard(
                        isRead = isSecondArticleRead,
                        onClick = {
                            val intent = Intent(context, SecondActivity::class.java)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

fun shareText(context: Context, text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun LikeDislikeCounter() {
    var likes by rememberSaveable { mutableStateOf(0) }
    var dislikes by rememberSaveable { mutableStateOf(0) }
    var selected by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                if(selected == "like") {
                    likes--
                    selected = ""
                } else {
                    if (selected == "dislike") dislikes --
                        likes++
                        selected = "like"
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selected == "like") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            )
            {
                Icon(Icons.Default.ThumbUp, contentDescription = "like")
            }
            Text(
                text = "$likes",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp
                )

            Spacer(modifier = Modifier.width(10.dp)
            )

            IconButton(
                onClick = {
                    if (selected == "dislike") {
                        dislikes--
                        selected = ""
                    } else {
                        if (selected == "like") likes --
                        dislikes++
                        selected = "dislike"
                    }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selected == "dislike") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(painterResource(id = R.drawable.thumb_down), contentDescription = "dislike")
            }
            Text(
                text = "$dislikes",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun ArticleCard(isRead : Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isRead) Purple80 else PurpleGrey80
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
            .clip(shape = RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.mainHeadingSecondPage),
            fontSize = Typography.bodyLarge.fontSize,
            lineHeight = Typography.bodyLarge.lineHeight,
            fontFamily = Typography.bodyLarge.fontFamily,
            fontWeight = Typography.bodyLarge.fontWeight,
            letterSpacing = Typography.bodyLarge.letterSpacing,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
fun MainScreenPreview(isRead: Boolean) {
    MainActivityScreen(isRead)
}