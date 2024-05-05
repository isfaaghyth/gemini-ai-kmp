package ui

sealed class GeminiRoute(val name: String) {
    data object Main : GeminiRoute("main")
    data object Detail : GeminiRoute("detail")
}