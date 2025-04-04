@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

//import android.graphics.fonts.FontStyle
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontStyle
import androidx.core.content.ContextCompat.startActivity
import ru.urfu.droidpractice1.MainActivity
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DataHolder

@Composable
fun MainActivityScreen() {
    val context = LocalContext.current
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    } ,
                    actions = { ShareButton(getArticleText()) }
                )
            }) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LikeDislikeButtons()
                    Text(
                        text = stringResource(id = R.string.heading1),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = stringResource(id = R.string.general_description),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    DownloadImg(DataMainActivity.imageUrl)
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    )
                    {
                        DataMainActivity.abilities.forEach { ability ->
                            AbilityItem(ability)
                        }
                    }
                    TransferTwoArticleButton(context)
                }
            }
        }
    }
}


/**
 * Выводит экран в Preview
 */
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}

/**
 * Вставляет систематизированный текст
 * @param ability - экземпляр класса способностей персонажа
 */
@Composable
fun AbilityItem(ability: Ability) {
    Column(modifier = Modifier
        .fillMaxSize()) {
        Text(
            text = ability.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = ability.type,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = ability.description,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

/**
 * Вставляет изображение по URL
 */
@Composable
fun DownloadImg(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

/**
 * Реализует кнопку "поделиться"
 */
@Composable
fun ShareButton(articleText: String) {
    val context = LocalContext.current

    IconButton(
        onClick = { shareArticle(context, articleText) }
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Поделиться"
        )
    }
}

/**
 * Упаковывает текст статьи и вызывает окно выбора мессенжера
 */
fun shareArticle(context: Context, articleText: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, articleText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Поделиться статьей")
    context.startActivity(shareIntent)
}

/**
 * Собирает текст статьи
 */
@Composable
fun getArticleText(): String {
    val abilitiesStringList = DataMainActivity.abilities.map { ability ->
        "${ability.name}\n${ability.type}\n${ability.description}"}
    return """
        ${stringResource(id = R.string.heading1)}
        ${stringResource(id = R.string.general_description)}
        ${abilitiesStringList.joinToString("\n\n")}
    """
}

/**
 * Кнопки лайков и дизлайков с счетчиком
 */
@Composable
fun LikeDislikeButtons() {
    var likes by rememberSaveable  { mutableStateOf(0) }
    var dislikes by rememberSaveable  { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { likes++ }) {
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Like")
        }
        Text(text = "$likes", fontSize = 18.sp)

        IconButton(onClick = { dislikes++ }) {
            Icon(imageVector = Icons.Default.ThumbDown, contentDescription = "Dislike")
        }
        Text(text = "$dislikes", fontSize = 18.sp)
    }
}

/**
 * Кнопка перехода на вторую статью
 */
@Composable
fun TransferTwoArticleButton(context:Context){
//    var isActive by remember { mutableStateOf(readSecondActivity) }
    val readSecondActivity = remember { mutableStateOf(DataHolder.readSecondActivity) }

    // Отслеживаем изменения в DataHolder
    LaunchedEffect(DataHolder.readSecondActivity) {
        snapshotFlow { DataHolder.readSecondActivity }.collect {
        }
    }
    Button(onClick = {
        val intent = Intent(context, SecondActivity::class.java)
//        intent.putExtra("read_second_activity", readSecondActivity)
        context.startActivity(intent)
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (readSecondActivity.value) Color.Gray else Color.Blue
        )) {
        Text("Читать о возвышениях")
    }
}


