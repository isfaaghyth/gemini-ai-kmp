package domain.usecase

import data.response.GeminiResponse
import domain.interactor.GeminiRepository
import domain.model.ContentModel

class GetContentUseCase constructor(
    private val repository: GeminiRepository
) {
    
    suspend operator fun invoke(content: String): ContentModel {
        val request = repository.request(content)
        return map(request)
    }
    
    private fun map(response: GeminiResponse): ContentModel {
        val responseText = response.candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
        
        return ContentModel(
            text = responseText ?: ""
        )
    }
}