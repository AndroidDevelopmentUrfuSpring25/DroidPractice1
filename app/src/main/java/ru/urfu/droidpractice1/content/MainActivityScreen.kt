@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import coil.compose.rememberAsyncImagePainter


@Composable
fun MainActivityScreen() {
    var likes by rememberSaveable { mutableStateOf(0) }
    var dislikes by rememberSaveable { mutableStateOf(0) }
    var isSwitchChecked by rememberSaveable { mutableStateOf( false) }

    val switchStateLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val state = result.data?.getBooleanExtra("switchState", false) ?: false
            isSwitchChecked = state
        }
    }
    val context = LocalContext.current

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title_toolbar),
                            fontSize = 24.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    actions = {
                        IconButton(onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, context.getString(R.string.first_article_title))
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.share_via))
                            context.startActivity(shareIntent)
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_share),
                                contentDescription = null
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.first_article_title),
                        fontSize = 24.sp,
                        lineHeight = 24.sp * 1.25f,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        IconButton(onClick = { likes++ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_like),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = if (likes != 0) "$likes" else "",
                            modifier = Modifier.width(30.dp)
                        )

                        IconButton(onClick = { dislikes++ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_dislike),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = if (dislikes != 0) "$dislikes" else "",
                            modifier = Modifier.width(30.dp)
                        )
                    }

                    Text(
                        text = stringResource(R.string.first_article_paragraph_1),
                        fontSize = 16.sp,
                        lineHeight = 16.sp * 1.5f,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(828f / 466f)
                            .clip(RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter("https://arsaman.ru/_nw/249/17439204.webp"),
                            contentDescription = R.string.image_description.toString(),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = stringResource(R.string.first_article_paragraph_2),
                        fontSize = 16.sp,
                        lineHeight = 16.sp * 1.5f,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                    Text(
                        text = stringResource(R.string.first_article_paragraph_3),
                        fontSize = 16.sp,
                        lineHeight = 16.sp * 1.5f,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    Button(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .alpha(if (isSwitchChecked) 0.5f else 1.0f),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            val intent = Intent(context, SecondActivity::class.java)
                            intent.putExtra("switchState", isSwitchChecked)
                            switchStateLauncher.launch(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.second_article_title),
                            fontSize = 16.sp,
                            lineHeight = 16.sp * 1.5f,
                            fontFamily = FontFamily.SansSerif,
                            color = colorResource(R.color.body_text_color),
                            modifier = Modifier
                                .padding(vertical = 10.dp)
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
    MainActivityScreen()
}