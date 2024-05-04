import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}

fun main() = application {
    initKoin()
    Window(onCloseRequest = ::exitApplication, title = "book-gemini") {
        App()
    }
}