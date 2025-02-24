@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.model.ARTICLE_1_HERO_IMAGE_URL
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(onShareClick: () -> Unit = {}, onOtherArticleClick: () -> Unit = {}, isRead: Boolean = false) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        IconButton(onClick = { onShareClick() }) {
                            Icon(Icons.Filled.Share, contentDescription = "Поделиться")
                        }
                    }
                )
            }) { innerPadding ->
            ArticleContent(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                onOtherArticleClick = onOtherArticleClick,
                isRead = isRead
            )
        }
    }
}

@Composable
fun ArticleContent(modifier: Modifier, onOtherArticleClick: () -> Unit = {}, isRead: Boolean) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            Text(stringResource(R.string.article_1_title), style = Typography.titleLarge)
            LikesComponent()
        }
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            Text(
                stringResource(R.string.article_1_content_1),
                style = Typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(stringResource(R.string.article_1_content_2), style = Typography.bodyLarge)
            Text(
                stringResource(R.string.article_1_content_3),
                style = Typography.bodyLarge,
            )
        }
        AsyncImage(
            model = ARTICLE_1_HERO_IMAGE_URL,
            contentDescription = null,
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Column(modifier = Modifier.padding(vertical = 24.dp)) {
            Text(
                stringResource(R.string.article_1_content_4),
                style = Typography.bodyLarge,
            )
            Text(
                stringResource(R.string.article_1_content_5),
                style = Typography.bodyLarge,
            )
        }
        Text(stringResource(R.string.article_1_content_6), style = Typography.bodyLarge)
        OtherArticle(
            modifier = Modifier.padding(top = 24.dp),
            onOtherArticleClick = onOtherArticleClick,
            isRead = isRead
        )
    }
}

@Composable
fun LikesComponent(modifier: Modifier = Modifier) {
    var likes by rememberSaveable { mutableIntStateOf(0) }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { likes++ }) {
            Icon(
                Icons.Filled.ThumbUp,
                "Like"
            )
        }
        IconButton(onClick = { likes-- }) {
            Icon(
                Icons.Filled.ThumbUp,
                "Like",
                modifier = modifier.rotate(180f)
            )
        }
        Text(likes.toString(), style = Typography.bodyMedium)
    }
}

@Composable
fun OtherArticle(modifier: Modifier = Modifier, onOtherArticleClick: () -> Unit = {}, isRead: Boolean = false) {
    val textColor = if (isRead) {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onOtherArticleClick)
    ) {
        Text(
            stringResource(R.string.article_2_title),
            style = Typography.bodyMedium,
            color = textColor,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LikesComponentPreview() {
    LikesComponent()
}

@Preview(showBackground = true)
@Composable
fun OtherArticlePreview() {
    OtherArticle()
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}