package ui.uimodel

sealed class GeminiUiEvent {
    data class BasicRequest(val command: String) : GeminiUiEvent()
    data class RequestWithAttachment(val command: String, val image: ByteArray) : GeminiUiEvent()
    data object Loading : GeminiUiEvent()
    data object Reset : GeminiUiEvent()
}