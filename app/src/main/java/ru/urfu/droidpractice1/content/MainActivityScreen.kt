@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.BodyItalicTextStyle

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(isReadChecked: State<Boolean>, onButtonClick: () -> Unit) {
    val context = LocalContext.current
    var isLiked by rememberSaveable { mutableStateOf(false) }
    var isDisliked by rememberSaveable { mutableStateOf(false) }
    var likeCount by rememberSaveable { mutableStateOf(0) }
    var dislikeCount by rememberSaveable { mutableStateOf(0) }
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, stringResource(R.string.page1_alltext))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share")

    DroidPractice1Theme {
        val colorbackgr = MaterialTheme.colorScheme.background
        val colorbutton = if (isReadChecked.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
        Scaffold(
            modifier = Modifier
                .background(colorbackgr)
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        IconButton(onClick = { startActivity(context, shareIntent, null) }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share"
                            )
                        }
                    }
                )
            }) { padding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(colorbackgr)
                    .padding(padding)
                    .padding(16.dp)

            ) {
                Text(
                    stringResource(R.string.page1_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    stringResource(R.string.page1_text1_cursiv),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = BodyItalicTextStyle
                )
                GlideImage(
                    model = "https://static.dzeninfra.ru/s3/zen-lib/1.003.1/dzen-layout/lz5XeGt8fsa/1e0sT7183/6e606eDQ9O/uHT5KKjBEGB6pKHznogvtxzxeLOnSIqRJneTdp6sEEL35RSGuiNhOFVzEB0kRQxIx6JYucGoipozeW1a9FFMCPhh9jexmQU2PJrlt9HeM-UUriTi5B-U22dgzSCJsFNCqN5fmrptP2o7ehAVO2oma9nWMJehh_q1STkvCuIMGBSsfaZp7s4Ac2_rofXvs3WGF-tXjJFZ3q80MJJJmekGT-3NBYuJRjBXPBNxcHxsIEb5wyG3hLnrpVMeAir7BjBwsUaJf83zJUpplbvq9r4zlzDfc5-NecGRHB2sfKzbOUHT6xmiq0MHTSAcWlR8UQFo_d8ZtZGB3ohkEy8H-hUcS6RQhlXB5Q5Rd4y-yuWZO4Q-_FCHnG_ThRQejkCo1w9gyvMdgYBhFEwyO0RpSFQMX_TYA5ero_OVXyg9AuY_Mm2vUJFrzdkARF6GvcjSlQS3ANFUobB7wrEXIYJCneA8cffdIZ2DZx1ZKi5Ac0xeLl_J9gmNp7nbql8YDwXSPxpDq0uQX9biL3JtkJ_T8p4Uhhr6T5mQYMygETWaXYHCK1LQ1zqMqH43fRc7aGFHbQhe0sc7kaeizK5QDwoIwiMacq1mmmb29xJDdoeZ5Pm_I4AD9mWsj1_NhBYRjFGl7wlS_M8gh71pJl4vKUVuenQMWP7GDqCFucqqQSQZAe4tOkiFdJRu7fgTQHqWvsXdmiS1CMtsn6Nd57I3FZpeu_cPTvfoJa2SSyddBhlwbG9CAFD60wS6ub_8vnEfGDj3MBNAmnarU9DBB2ZBqpDI8okgqhPIYpaxYfWZECu5f6z7AHHv6giKr0kSZi0nSGpbZgxv4MMhvbK-8opwHA4Z8zM_foptpnb2xy9Md4K179mPJYMA82W8l1_VuwULjF2IzS9918wkroBJIHo1AUFxZUA3Us_8KZewuM20RBk3If4XD1ulbptB1OsjdXiTvcTlry2CC-5am6NL97U5LaJbuswzQf7jHZuzbiFFAh1uQUN7Gm_d5DueoL7nimohHg7EDytcrkGkdffiKFJ8pqX4-qY5gSPBeaOTQ-KRMB63XozpBmHqzSWHt0sYbBchRUhqXRxv6sc1mp6KxLp2Di0a1QgqV4VdjGv32SBwZ5CJ69mfFrEH6W6BoWj9tCktnWe48RlD8uMYgpBkJWQxHW1UUWYzev3dC5u4udOrfgwiOc4XLlycbJB34esTeV6fuMzXhSCqEfN_pKNe0Ko8KaZGpu4hVPjXNZiyXzx1ARh6SUZNNEHy_AKsn4rwskkJIxPSCDlNkGC6Y-jsNUdeopXX3IUZogr3W7KxZs2oEC-lZZjQHH742DGwkEkFSgUbVlBdQBNO1e0_jJWXzq9oABYZ0RcJfpFhrX3AzgJYf6yDxPuvNr4z13WTgV_xhTAinVqg0zlm_f88go9FGG0QG01AcXkfYPXmG6qClNaKUisjL-4jH1OlV6d_0tUUcWaot-PfvxGRC-FFhrpx9qsxP6RghMswQePUHI-feBxeHg9VY3pdHUPi_giao4LetWk6NS_SKAxWg1alSeHHLU1HjKrR6YESgB_OVaC0cOaNFja8RrrMG3_q6iWrnmY2TgEeY0NlfDR70tA5mZCZ6JZpHz0j5jczaoxXu1fM1SlrXoCxx-OlGr4L_HmasWXxiSc1mFGDwytG_vk6qJZ9JlseInpVYUA0fdP3F6GFtsywSyYUKeMHGHa-YoVTysM2a2G8v9jRuzaXAtl_grFs75owDbJei8svcN7cNYiXRQdCLjFSZn9UK2vp1zm2uqnWuW8wCSH-CA5upHSISe3XC0V3t5Th5bQ7oB_3X5SQaveGNRm9Q6XzJmDdzji9rHg2dS88VH9mZBBe2fI8rpmtx5NCOjUZwCMrb4pKrl3ExxZ7Yomn9uWBGog99nutnGHNkB4Rp1yd2yNi28MkhKxKP1kLKXNgTXAIa_PBII-liseeVCIVKv4jH1-kT61xxNUPbXmIl-c",
                    contentDescription = "Image 2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    stringResource(R.string.page1_text1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
                Text(
                    stringResource(R.string.page1_text2_cursiv),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = BodyItalicTextStyle
                )
                GlideImage(
                    model = "https://static.dzeninfra.ru/s3/zen-lib/1.003.1/dzen-layout/lz5XeGt8fsa/1e0sT7183/6e606eDQ9O/uHT5KKjBEGB6pKHznogvtxzxeLOnSIqRJneTdp6sEEL35RSGuiNhOFVzEB0kRQxIx6JYucGoipozeW1a9FFMCPhh9jexmQU2PJrlt9HeM-UUribn5xuRgmRokSeP5VoTqNhfmrptP2o7ehAVO2oma9nWMJehh_q1STkvCuIMGBSsfaZp7s4Ac2_rofXvs3WGF-tXjJFZ3q80MJJJmekGT-3NBYuJRjBXPBNxcHxsIEb5wyG3hLnrpVMeAir7BjBwsUaJf83zJUpplbvq9r4zlzDfc5-NecGRHB2sfKzbOUHT6xmiq0MHTSAcWlR8UQFo_d8ZtZGB3ohkEy8H-hUcS6RQhlXB5Q5Rd4y-yuWZO4Q-_FCHnG_ThRQejkCo1w9gyvMdgYBhFEwyO0RpSFQMX_TYA5ero_OVXyg9AuY_Mm2vUJFrzdkARF6GvcjSlQS3ANFUobB7wrEXIYJCneA8cffdIZ2DZx1ZKi5Ac0xeLl_J9gmNp7nbql8YDwXSPxpDq0uQX9biL3JtkJ_T8p4Uhhr6T5mQYMygETWaXYHCK1LQ1zqMqH43fRc7aGFHbQhe0sc7kaeizK5QDwoIwiMacq1mmmb29xJDdoeZ5Pm_I4AD9mWsj1_NhBYRjFGl7wlS_M8gh71pJl4vKUVuenQMWP7GDqCFucqqQSQZAe4tOkiFdJRu7fgTQHqWvsXdmiS1CMtsn6Nd57I3FZpeu_cPTvfoJa2SSyddBhlwbG9CAFD60wS6ub_8vnEfGDj3MBNAmnarU9DBB2ZBqpDI8okgqhPIYpaxYfWZECu5f6z7AHHv6giKr0kSZi0nSGpbZgxv4MMhvbK-8opwHA4Z8zM_foptpnb2xy9Md4K179mPJYMA82W8l1_VuwULjF2IzS9918wkroBJIHo1AUFxZUA3Us_8KZewuM20RBk3If4XD1ulbptB1OsjdXiTvcTlry2CC-5am6NL97U5LaJbuswzQf7jHZuzbiFFAh1uQUN7Gm_d5DueoL7nimohHg7EDytcrkGkdffiKFJ8pqX4-qY5gSPBeaOTQ-KRMB63XozpBmHqzSWHt0sYbBchRUhqXRxv6sc1mp6KxLp2Di0a1QgqV4VdjGv32SBwZ5CJ69mfFrEH6W6BoWj9tCktnWe48RlD8uMYgpBkJWQxHW1UUWYzev3dC5u4udOrfgwiOc4XLlycbJB34esTeV6fuMzXhSCqEfN_pKNe0Ko8KaZGpu4hVPjXNZiyXzx1ARh6SUZNNEHy_AKsn4rwskkJIxPSCDlNkGC6Y-jsNUdeopXX3IUZogr3W7KxZs2oEC-lZZjQHH742DGwkEkFSgUbVlBdQBNO1e0_jJWXzq9oABYZ0RcJfpFhrX3AzgJYf6yDxPuvNr4z13WTgV_xhTAinVqg0zlm_f88go9FGG0QG01AcXkfYPXmG6qClNaKUisjL-4jH1OlV6d_0tUUcWaot-PfvxGRC-FFhrpx9qsxP6RghMswQePUHI-feBxeHg9VY3pdHUPi_giao4LetWk6NS_SKAxWg1alSeHHLU1HjKrR6YESgB_OVaC0cOaNFja8RrrMG3_q6iWrnmY2TgEeY0NlfDR70tA5mZCZ6JZpHz0j5jczaoxXu1fM1SlrXoCxx-OlGr4L_HmasWXxiSc1mFGDwytG_vk6qJZ9JlseInpVYUA0fdP3F6GFtsywSyYUKeMHGHa-YoVTysM2a2G8v9jRuzaXAtl_grFs75owDbJei8svcN7cNYiXRQdCLjFSZn9UK2vp1zm2uqnWuW8wCSH-CA5upHSISe3XC0V3t5Th5bQ7oB_3X5SQaveGNRm9Q6XzJmDdzji9rHg2dS88VH9mZBBe2fI8rpmtx5NCOjUZwCMrb4pKrl3ExxZ7Yomn9uWBGog99nutnGHNkB4Rp1yd2yNi28MkhKxKP1kLKXNgTXAIa_PBII-liseeVCIVKv4jH1-kT61xxNUPbXmIl-c",
                    contentDescription = "Image 3",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    stringResource(R.string.page1_text2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
                // Лайки/дизлайки
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Like",
                        tint = if (likeCount > 0) Color.Blue else Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (likeCount == 0) {
                                    likeCount++
                                    if (dislikeCount > 0) dislikeCount--
                                } else {
                                    likeCount--
                                }
                            }
                    )
                    Text(
                        text = "$likeCount",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Filled.ThumbDown,
                        contentDescription = "Dislike",
                        tint = if (dislikeCount > 0) Color.Red else Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (dislikeCount == 0) {
                                    dislikeCount++
                                    if (likeCount > 0) likeCount--
                                } else {
                                    dislikeCount--
                                }
                            }
                    )
                    Text(
                        text = "$dislikeCount",  // Показать количество дизлайков
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorbutton,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(text = stringResource(id = R.string.article2_title))
                }
            }
        }
    }
}

