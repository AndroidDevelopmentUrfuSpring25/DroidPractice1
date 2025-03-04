@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    onNavigateToSecondArticle: () -> Unit,
    isSecondArticleRead: Boolean,
    likeCount: Int = 0,
    dislikeCount: Int = 0,
    isLiked: Boolean = false,
    isDisliked: Boolean = false,
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.first_article_header1),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.first_article_date),
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://media.discordapp.net/attachments/914593555526320151/1346271106495152229/18886661-f53b-4ad7-ba1a-e3342c22996b.webp?ex=67c7942c&is=67c642ac&hm=f4709b4fa22d9c83429e0ef452925aaad0c98b2d957121b892f757c0ffd363c2&=&format=webp&width=1709&height=856")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Game screenshot",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = stringResource(id = R.string.first_article_par1),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Text(
                    text = stringResource(id = R.string.first_article_header2),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = stringResource(id = R.string.first_article_par2),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = stringResource(id = R.string.first_article_par3),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onLikeClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isLiked) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (isLiked) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Icon(Icons.Default.ThumbUp, contentDescription = "Like")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = likeCount.toString())
                    }

                    Button(
                        onClick = onDislikeClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDisliked) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (isDisliked) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Icon(Icons.Default.ThumbDown, contentDescription = "Dislike")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = dislikeCount.toString())
                    }

                    Button(onClick = onShareClick) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Поделиться")
                    }
                }

                Button(
                    onClick = onNavigateToSecondArticle,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Перейти ко второй статье")
                }

                if (isSecondArticleRead) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(
                            text = "Вторая статья прочитана",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
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
    MainActivityScreen(
        onNavigateToSecondArticle = {},
        isSecondArticleRead = false,
        likeCount = 0,
        dislikeCount = 0,
        isLiked = false,
        isDisliked = false
    )
}