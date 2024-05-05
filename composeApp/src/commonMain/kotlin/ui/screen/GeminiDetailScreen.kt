package ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.uimodel.GeminiUiModel
import utils.parseHtml

@Composable
fun GeminiDetailScreen(
    uiModel: GeminiUiModel,
    onBack: () -> Unit
) {
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onBack()
            }
    ) {
        Text(
            text = uiModel.summarization.parseHtml(),
            modifier = Modifier
                .padding(30.dp)
        )
    }
}