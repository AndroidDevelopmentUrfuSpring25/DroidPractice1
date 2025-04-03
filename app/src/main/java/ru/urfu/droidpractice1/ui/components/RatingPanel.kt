package ru.urfu.droidpractice1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

@Composable
fun RatingPanel(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    upvote: () -> Unit = {},
    downvote: () -> Unit = {}
    ) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { upvote() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_thumb_up),
                contentDescription = "Лайк"
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text = rating.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = { downvote() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_thumb_down),
                contentDescription = "Дизлайк"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingPanelPreview(){
    RatingPanel()
}