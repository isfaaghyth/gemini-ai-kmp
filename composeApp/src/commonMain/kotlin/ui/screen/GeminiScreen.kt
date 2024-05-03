package ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun GeminiScreen(
    navigateToDetailPageClicked: () -> Unit
) {
    Text("Hi, world!")
    Button(
        onClick = { navigateToDetailPageClicked() }
    ) {
        Text("Click here.")
    }
}