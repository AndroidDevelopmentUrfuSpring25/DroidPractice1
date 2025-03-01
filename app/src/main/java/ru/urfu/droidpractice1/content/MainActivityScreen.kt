@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Purple80
import ru.urfu.droidpractice1.ui.theme.PurpleGrey80

@Composable
fun MainActivityScreen(
    isRead: Boolean,
    count: Int,
    selected: Int,
    onStateChanged: (Int, Int) -> Unit,
    artClick: () -> Unit,
) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, stringResource(R.string.all_text))
        type = "text/plain"
    }
    var currentCount by rememberSaveable { mutableIntStateOf(count) }
    var currentSelected by rememberSaveable { mutableIntStateOf(selected) }
    val shareIntent = Intent.createChooser(sendIntent, "Share")
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
                        IconButton(onClick = { startActivity(context, shareIntent, null) }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Gray,
                    )
                )
            }
        )
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = stringResource(R.string.article_text_0),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.date),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                )
                Row {
                    IconButton(
                        onClick = {
                            when (currentSelected) {
                                0 -> {
                                    currentSelected = 1
                                    currentCount += 1
                                }

                                1 -> {
                                    currentSelected = 0
                                    currentCount -= 1
                                }

                                -1 -> {
                                    currentSelected = 1
                                    currentCount += 2
                                }
                            }

                            onStateChanged(currentCount, currentSelected)
                        }
                    ) {
                        Icon(
                            imageVector = if (currentSelected == 1) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                            contentDescription = "Like",
                            tint = if (currentSelected == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = {
                            when (currentSelected) {
                                0 -> {
                                    currentSelected = -1
                                    currentCount -= 1
                                }

                                -1 -> {
                                    currentSelected = 0
                                    currentCount += 1
                                }

                                1 -> {
                                    currentSelected = -1
                                    currentCount -= 2
                                }
                            }
                            onStateChanged(currentCount, currentSelected)
                        }
                    ) {
                        Icon(
                            imageVector = if (currentSelected == -1) Icons.Filled.ThumbDown else Icons.Outlined.ThumbDown,
                            contentDescription = "Dislike",
                            tint = if (currentSelected == -1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = "$currentCount",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)

                    )
                }
                Spacer(Modifier.height(15.dp))
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://ss.sport-express.ru/userfiles/materials/207/2078736/large.jpg")
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                )
                Text(
                    text = stringResource(id = R.string.article_text_1)
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.article_subtitle_1),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)


                )
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://ss.sport-express.ru/userfiles/materials/207/2078735/volga.jpg")
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(id = R.string.article_text_2)
                )
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://ss.sport-express.ru/userfiles/materials/207/2078733/volga.jpg")
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(id = R.string.article_text_3)

                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.article_subtitle_2),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://ss.sport-express.ru/userfiles/materials/207/2078729/volga.jpg")
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(id = R.string.article_text_4)

                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.article_subtitle_3),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://ss.sport-express.ru/userfiles/materials/207/2078730/volga.jpg")
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(id = R.string.article_text_5)

                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.article_subtitle_4),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                )
                Text(
                    text = stringResource(id = R.string.article_text_6)

                )
                Spacer(Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .background(if (isRead) PurpleGrey80 else Purple80)
                        .clickable { artClick() }
                ) {
                    Text(
                        text = stringResource(id = R.string.second_article_title),
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(start = 14.dp, top = 14.dp, bottom = 14.dp, end = 24.dp)
                    )
                }
            }
        }
    }
}
