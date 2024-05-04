package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GeminiDetailScreen(
    content: String,
    onBack: () -> Unit
) {
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onBack()
            }
    ) {
        Text(text = content)
    }
}