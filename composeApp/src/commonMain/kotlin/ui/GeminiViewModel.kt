package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.GetContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ui.uimodel.GeminiUiEvent
import ui.uimodel.GeminiUiModel

class GeminiViewModel constructor(
    private val getContentUseCase: GetContentUseCase
) : ViewModel() {
    
    private val _event = MutableSharedFlow<GeminiUiEvent>(replay = 50)
    
    private val _state = MutableStateFlow(GeminiUiModel())
    val state = _state.asStateFlow()
    
    init {
        viewModelScope.launch {
            _event.distinctUntilChanged()
                .collect { event ->
                    when(event) {
                        is GeminiUiEvent.Summarize -> summarizeBookFromImage(event.command)
                        is GeminiUiEvent.Reset -> _state.update { GeminiUiModel() }
                    }
                }
        }
    }
    
    fun sendAction(event: GeminiUiEvent) {
        _event.tryEmit(event)
    }
    
    fun resetState() {
        _event.tryEmit(GeminiUiEvent.Reset)
    }
    
    private fun summarizeBookFromImage(content: String) {
        viewModelScope.launch {
            val result = getContentUseCase(content)
            
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        summarization = result.text
                    )
                }
            }
        }
    }
}