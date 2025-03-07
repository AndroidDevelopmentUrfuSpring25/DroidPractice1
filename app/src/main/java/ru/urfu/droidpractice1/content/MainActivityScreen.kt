@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.components.RatingPanel
import ru.urfu.droidpractice1.ui.components.ReadStatePanel
import ru.urfu.droidpractice1.ui.components.ShareButton
import ru.urfu.droidpractice1.utils.debugPlaceholder

@Composable
fun MainActivityScreen(
    rating : Int = 0,
    isLessonRead: Boolean = false,
    onShareClick: (text: String) -> Unit = {},
    onUpvoteClick: () -> Unit = {},
    onDownvoteClick: () -> Unit = {},
    onLessonClick: () -> Unit = {}
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.course_title)
                        )
                    },
                    actions = {
                        ShareButton(
                            text = stringResource(R.string.course_description),
                            onShareClicked = onShareClick
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = stringResource(R.string.course_photo),
                    contentDescription = null,
                    placeholder = debugPlaceholder(R.drawable.debug_placeholder_760x380),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = stringResource(R.string.course_description),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                RatingPanel(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    rating = rating,
                    upvote = onUpvoteClick,
                    downvote = onDownvoteClick
                )

                Text(
                    text = stringResource(R.string.course_subtitle),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                Text(
                    text = stringResource(R.string.program_description),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(vertical = 16.dp)
                        .alpha(if (isLessonRead) 0.5f else 1f)
                        .clickable(onClick = onLessonClick)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                    )
                    {
                        AsyncImage(
                            model = stringResource(R.string.lesson_card_photo),
                            contentDescription = null,
                            placeholder = debugPlaceholder(R.drawable.debug_placeholder_95x95),
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier
                        )
                        {
                            ReadStatePanel(isLessonRead = isLessonRead)
                            Text(
                                stringResource(R.string.lesson_card_title),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                            )
                            Text(
                                stringResource(R.string.lesson_card_description),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                            )
                        }
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