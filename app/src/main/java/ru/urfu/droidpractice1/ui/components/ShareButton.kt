package ru.urfu.droidpractice1.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity

@Composable
fun ShareButton(
    text: String,
    onShareClicked: (text: String) -> Unit = {}
) {
    IconButton(onClick = { onShareClicked(text) }) {
        Icon(
            Icons.Default.Share,
            contentDescription = "Поделиться"
        )
    }
}