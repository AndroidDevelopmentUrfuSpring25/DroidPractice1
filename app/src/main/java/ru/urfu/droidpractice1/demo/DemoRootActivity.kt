package ru.urfu.droidpractice1.demo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.urfu.droidpractice1.utils.startOtherActivity

class DemoRootActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoRootContent()
        }

        // todo работа с темами https://developer.android.com/codelabs/jetpack-compose-theming
    }

    @Composable
    private fun DemoRootContent() {
        Column(Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { startOtherActivity(DemoViewsActivity::class.java) }
            ) {
                Text("Views")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { startOtherActivity(DemoComposeActivity::class.java) }) {
                Text("Compose")
            }
        }
    }

    @Preview
    @Composable
    private fun ContentPreview() {
        DemoRootContent()
    }
}