package domain.usecase

import domain.interactor.GeminiRepository
import domain.mapper.GeminiMapper
import domain.model.ContentModel

class GetContentWithImageUseCase(
    private val repository: GeminiRepository
) {
    
    suspend operator fun invoke(content: String, image: ByteArray): ContentModel {
        val request = repository.requestWithImage(content, image)
        return GeminiMapper.map(request)
    }
}