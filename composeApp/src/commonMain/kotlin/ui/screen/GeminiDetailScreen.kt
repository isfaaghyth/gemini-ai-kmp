package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import ui.uimodel.GeminiUiEvent

@Composable
fun GeminiDetailScreen(
    viewModel: GeminiViewModel,
    content: String,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        println(content)
        viewModel.sendAction(GeminiUiEvent.Summarize(content))
    }
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onBack()
            }
    ) {
        Text(text = state.summarization)
    }
}