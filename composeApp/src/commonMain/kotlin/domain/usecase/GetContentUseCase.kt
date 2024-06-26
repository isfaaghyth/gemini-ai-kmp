package domain.usecase

import domain.interactor.GeminiRepository
import domain.mapper.GeminiMapper
import domain.model.ContentModel

class GetContentUseCase(
    private val repository: GeminiRepository
) {
    
    suspend operator fun invoke(content: String): ContentModel {
        val request = repository.request(content)
        return GeminiMapper.map(request)
    }
}