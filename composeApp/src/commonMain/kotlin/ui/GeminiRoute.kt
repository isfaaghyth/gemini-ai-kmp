package ui

sealed class GeminiRoute(val name: String) {
    data object Main : GeminiRoute("main")
    data object Detail : GeminiRoute("detail/{${Arguments.CONTENT_ID}}")

    object Arguments {
        const val CONTENT_ID = "contentId"
    }
}