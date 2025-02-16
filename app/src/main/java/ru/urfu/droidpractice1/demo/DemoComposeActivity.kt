package ru.urfu.droidpractice1.demo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.demo.DemoResultActivity.Companion.SELECTED_MODEL_KEY
import ru.urfu.droidpractice1.demo.model.IMAGE_URL

@OptIn(ExperimentalMaterial3Api::class)
class DemoComposeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Compose") },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = null,
                            Modifier.clickable { onBackPressedDispatcher.onBackPressed() }
                        )
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // https://kotlinlang.ru/docs/delegated-properties.html
                var name by remember { mutableStateOf("") }
                var isAnonymously by remember { mutableStateOf(false) }

                val radioOptions = stringArrayResource(id = R.array.survey_models)
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

                AsyncImage(
                    model = IMAGE_URL,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        stringResource(R.string.survey_hint),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // https://developer.android.com/develop/ui/compose/text/user-input
                if (isAnonymously.not()) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(stringResource(id = R.string.name_hint)) },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // https://developer.android.com/develop/ui/compose/components/switch
                    Switch(
                        checked = isAnonymously,
                        onCheckedChange = { isAnonymously = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.anonymously),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                RadioButtonSingleSelection(Modifier, radioOptions, selectedOption, onOptionSelected)

                Button(
                    onClick = {
                        val intent =
                            Intent(this@DemoComposeActivity, DemoResultActivity::class.java).apply {
                                putExtra(SELECTED_MODEL_KEY, selectedOption)
                            }
                        startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.survey_button_done, selectedOption))
                }
            }
        }
    }

    // https://developer.android.com/develop/ui/compose/components/radio-button
    @Composable
    fun RadioButtonSingleSelection(
        modifier: Modifier = Modifier,
        radioOptions: Array<String>,
        selectedOption: String,
        onOptionSelected: (String) -> Unit
    ) {
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        Column(modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screen readers
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }


    @Preview
    @Composable
    private fun ContentPreview() = Content()
}