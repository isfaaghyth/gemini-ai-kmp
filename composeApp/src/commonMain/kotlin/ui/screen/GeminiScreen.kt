package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import ui.uimodel.GeminiUiModel

@Composable
fun GeminiScreen(
    uiModel: GeminiUiModel,
    onSummarizeBook: (String) -> Unit,
    navigateToDetailPageClicked: (String) -> Unit,
) {
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
                onSummarizeBook("Get 10 highlight from Design Sprint from Jake Knapp's book, give me with bullet items format")
            }
        ) {
            Text("Click here.")
        }
    }
}