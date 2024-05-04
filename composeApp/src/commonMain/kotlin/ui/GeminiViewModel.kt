package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.GetContentUseCase
import domain.usecase.GetContentWithImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ui.uimodel.GeminiUiEvent
import ui.uimodel.GeminiUiModel

class GeminiViewModel constructor(
    private val getContentUseCase: GetContentUseCase,
    private val getContentWithImageUseCase: GetContentWithImageUseCase
) : ViewModel() {

    private val _event = MutableSharedFlow<GeminiUiEvent>(replay = 50)

    private val _state = MutableStateFlow(GeminiUiModel())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _event.distinctUntilChanged()
                .collect(::update)
        }
    }

    fun sendAction(event: GeminiUiEvent) {
        _event.tryEmit(event)
    }

    fun resetState() {
        _event.tryEmit(GeminiUiEvent.Reset)
    }

    private fun update(event: GeminiUiEvent) {
        when (event) {
            is GeminiUiEvent.BasicRequest -> {
                sendAction(GeminiUiEvent.Loading)
                basicRequestWithCommand(event.command)
            }

            is GeminiUiEvent.RequestWithAttachment -> {
                sendAction(GeminiUiEvent.Loading)
                imageAttachmentRequestWithCommand(event.command, event.image)
            }

            is GeminiUiEvent.Loading -> shouldShowLoadingState()
            is GeminiUiEvent.Reset -> disposeLastState()
        }
    }

    private fun shouldShowLoadingState() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun basicRequestWithCommand(command: String) {
        viewModelScope.launch {
            val result = getContentUseCase(command)

            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        summarization = result.text
                    )
                }
            }
        }
    }

    private fun imageAttachmentRequestWithCommand(command: String, image: ByteArray) {
        viewModelScope.launch {
            val result = getContentWithImageUseCase(command, image)

            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        summarization = result.text
                    )
                }
            }
        }
    }

    private fun disposeLastState() {
        _state.update { GeminiUiModel() }
    }
}