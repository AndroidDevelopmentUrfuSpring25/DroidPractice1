package ru.urfu.droidpractice1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

@Composable
fun ReadStatePanel(
    isLessonRead: Boolean = false
) {
    Row() {
        Icon(
            painter = painterResource(
                if (isLessonRead) R.drawable.ic_check_circle else R.drawable.ic_lock_open),
            contentDescription = if (isLessonRead) "Прочитан" else "Открыт",
            modifier = Modifier
                .size(15.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = if (isLessonRead) "Прочитан" else "Открыт",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReadStatePanelPreview() {
    ReadStatePanel()
}