import androidx.compose.runtime.*
import di.Di
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

import di.network.GeminiApi
import di.GeminiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val useCase = Di.provideUseCase()
    
    GlobalScope.launch {
        val request = useCase("What is Gemini AI?")
        
        withContext(Dispatchers.Main) {
            println(request.text)
        }
    }
}