package ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import ui.uimodel.GeminiUiModel
import utils.BOOK_HIGHLIGHT_COMMAND

@Composable
fun GeminiScreen(
    uiModel: GeminiUiModel,
    onSummarizeBook: (String, ByteArray) -> Unit,
    navigateToDetailPageClicked: (String) -> Unit,
) {
    var selectedImage by remember { mutableStateOf(listOf<ImageBitmap>()) }
    val coroutineScope = rememberCoroutineScope()

    val picker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = coroutineScope,
        onResult = { byteArrays ->
            byteArrays.firstOrNull()?.let {
                selectedImage = listOf(it.toImageBitmap())
                onSummarizeBook(BOOK_HIGHLIGHT_COMMAND, it)
            }
        }
    )

    LaunchedEffect(uiModel.summarization) {
        if (uiModel.summarization.isNotEmpty()) {
            navigateToDetailPageClicked(uiModel.summarization)
        }
    }

    AnimatedVisibility(uiModel.isLoading) {
        Dialog(
            onDismissRequest = {},
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(selectedImage) { image ->
                Image(
                    bitmap = image,
                    contentDescription = "Selected Image",
                    modifier =
                    Modifier
                        .size(240.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { picker.launch() }
        ) {
            Text("Pick and Summarize a Book")
        }
    }
}