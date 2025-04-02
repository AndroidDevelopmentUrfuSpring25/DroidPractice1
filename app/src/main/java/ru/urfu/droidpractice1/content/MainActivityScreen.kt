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
import android.content.res.Resources
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
import androidx.compose.ui.platform.LocalDensity
import ru.urfu.droidpractice1.SecondActivity
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.MainActivity

@Composable
fun MainActivityScreen(isSecondArticleRead: Boolean) {
    val context = LocalContext.current
    val textPadding10dp = with(LocalDensity.current) {
        context.resources.getDimension(R.dimen.textPadding10dp).toDp()
    }
    val textPadding16dp = with(LocalDensity.current) {
        context.resources.getDimension(R.dimen.textPadding16dp).toDp()
    }

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
                            shareText(context, R.string.shareTitle.toString())
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share"
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(textPadding10dp)
                    .verticalScroll(rememberScrollState())
            ){
                Text(
                    text = stringResource(id = R.string.mainHeadingFirstPage),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
                )

                LikeDislikeCounter()

                AsyncImage(
                    model = "https://img.championat.com/s/732x488/news/big/o/o/amur-ne-popal-v-plej-off-khl_1740999268648042281.jpg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = textPadding10dp, bottom = textPadding10dp, end = textPadding16dp, start = textPadding16dp),
                    contentDescription = "picture",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = stringResource(id = R.string.firstParagraphFirstPage),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                    modifier = Modifier
                        .padding(top = textPadding10dp, bottom = textPadding10dp)
                )
                Text(
                    text = stringResource(id = R.string.secondParagraphFirstPage),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                    modifier = Modifier
                        .padding(top = textPadding10dp, bottom = textPadding10dp)
                )
                Text(
                    text = stringResource(id = R.string.thirdParagraphFirstPage),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                    modifier = Modifier
                        .padding(top = textPadding10dp, bottom = textPadding10dp)
                )
                Text(
                    text = stringResource(id = R.string.fourthParagraphFirstPage),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                    modifier = Modifier
                        .padding(top = textPadding10dp, bottom = textPadding10dp)
                )
                AsyncImage(
                    model = "https://img.championat.com/i/b/g/17409990851598301748.jpg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = textPadding10dp, bottom = 5.dp, end = textPadding16dp, start = textPadding16dp),
                    contentDescription = "picture",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(id = R.string.fifthParagraphFirstPage),
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = textPadding16dp),
                    color = PurpleGrey40,
                    lineHeight = 12.sp,
                )

                ArticleCard(
                    isRead = isSecondArticleRead,
                    onClick = {
                        val intent = Intent(context, SecondActivity::class.java).apply {
                            putExtra(SecondActivity.KEY_IS_READ, isSecondArticleRead)
                        }
                        (context as MainActivity).secondActivityLauncher.launch(intent)
                    }
                )
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

@Composable
fun Counter(
    count: Int,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    isSelected: Boolean,
    selectedColor: Color
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = if (isSelected) selectedColor else MaterialTheme.colorScheme.onSurface
            )
        ) {
            icon()
        }
        Text(
            text = "$count",
            fontSize = 14.sp
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun LikeDislikeCounter() {
    var likes by rememberSaveable { mutableStateOf(0) }
    var dislikes by rememberSaveable { mutableStateOf(0) }
    var selected by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Counter(
            count = likes,
            icon = { Icon(Icons.Default.ThumbUp, contentDescription = "like") },
            onClick = {
                if(selected == "like") {
                    likes--
                    selected = ""
                } else {
                    if (selected == "dislike") dislikes--
                    likes++
                    selected = "like"
                }
            },
            isSelected = selected == "like",
            selectedColor = Color(0xFF2196F3)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Counter(
            count = dislikes,
            icon = { Icon(painterResource(id = R.drawable.thumb_down), contentDescription = "dislike") },
            onClick = {
                if(selected == "dislike") {
                    dislikes--
                    selected = ""
                } else {
                    if (selected == "like") likes--
                    dislikes++
                    selected = "dislike"
                }
            },
            isSelected = selected == "dislike",
            selectedColor = Color(0xFFF44336)
        )
    }
}

@Composable
fun ArticleCard(isRead : Boolean, onClick: () -> Unit) {
    val context = LocalContext.current
    val textPadding10dp = with(LocalDensity.current) {
        context.resources.getDimension(R.dimen.textPadding10dp).toDp()
    }

    val textPadding16dp = with(LocalDensity.current) {
        context.resources.getDimension(R.dimen.textPadding16dp).toDp()
    }

    val backgroundColor = if (isRead) Purple80 else PurpleGrey80
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(textPadding10dp)
            .clickable { onClick() }
            .clip(shape = RoundedCornerShape(textPadding16dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.mainHeadingSecondPage),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
            modifier = Modifier
                .padding(textPadding10dp)
        )
    }
}

@Composable
fun MainScreenPreview(isRead: Boolean) {
    MainActivityScreen(isRead)
}