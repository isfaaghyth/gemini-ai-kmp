package ui.uimodel

sealed class GeminiUiEvent {
    data class Summarize(val command: String) : GeminiUiEvent()
    data object Reset : GeminiUiEvent()
}