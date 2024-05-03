package domain.interactor

import data.response.GeminiResponse

interface GeminiRepository {
    
    suspend fun request(content: String): GeminiResponse
}