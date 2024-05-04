package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import ui.uimodel.GeminiUiModel

@Composable
fun GeminiScreen(
    uiModel: GeminiUiModel,
    onSummarizeBook: (String, ByteArray) -> Unit,
    navigateToDetailPageClicked: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val picker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = coroutineScope,
        onResult = { byteArrays ->
            byteArrays.firstOrNull()?.let {
                onSummarizeBook("Get 10 highlight from this book, give me with bullet items format", it)
            }
        }
    )

    LaunchedEffect(uiModel.summarization) {
        if (uiModel.summarization.isNotEmpty()) {
            navigateToDetailPageClicked(uiModel.summarization)
        }
    }

    Column {
        Text("Hi, world!")

        AnimatedVisibility(uiModel.isLoading) {
            Text(text = "Loading")
        }

        Button(
            onClick = {
                picker.launch()
            }
        ) {
            Text("Click here.")
        }
    }
}