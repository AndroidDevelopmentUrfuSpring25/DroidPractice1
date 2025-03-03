package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    private val viewModel: ArticleViewModel by viewModels()

    private val startSecondArticle = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            viewModel.setArticleRead(result.data?.getBooleanExtra("isRead", false) ?: false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticleScreen(
                        onNavigateToSecondArticle = {
                            startSecondArticle.launch(Intent(this, SecondActivity::class.java))
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleScreen(
    onNavigateToSecondArticle: () -> Unit,
    viewModel: ArticleViewModel
) {
    val context = LocalContext.current
    val likes by viewModel.likes.collectAsState()
    val dislikes by viewModel.dislikes.collectAsState()
    val isArticleRead by viewModel.isArticleRead.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Дуглас застрял между сборными Бразилии и России. Под угрозой легионерский вопрос «Зенита»",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = rememberAsyncImagePainter("https://img.championat.com/s/732x488/news/big/k/q/duglas-santos_17410168551046676303.jpg"),
            contentDescription = "Дуглас Сантос",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "У защитника есть возможность снова сменить гражданство.",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Сборная России объявила расширенный состав на предстоящий тренировочный сбор. В список попал защитник «Зенита» Дуглас Сантос — это его первое подобное включение.\n\n" +
                    "Дуглас играет за «Зенит» с 2019 года. В октябре 2024-го бразилец получил российский паспорт, а через несколько недель бразильца перестали считать легионером. «ФИФА рассмотрела заявление клуба и приняла положительное решение о смене футбольного гражданства игрока. Сине‑бело‑голубые получили соответствующее разъяснение, а также официальное уведомление от Российского футбольного союза — с этот момент Дуглас Сантос не считается легионером», — гласило сообщение в телеграм-канале «Зенита».",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.incrementLikes() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("👍 $likes")
            }
            
            Button(
                onClick = { viewModel.incrementDislikes() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("👎 $dislikes")
            }
        }

        Button(
            onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Дуглас застрял между сборными Бразилии и России. Под угрозой легионерский вопрос «Зенита»")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Поделиться")
        }

        Button(
            onClick = onNavigateToSecondArticle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(if (isArticleRead) "Вторая статья прочитана" else "Перейти к следующей статье")
        }

        if (isArticleRead) {
            Text(
                text = "Вторая статья прочитана",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}