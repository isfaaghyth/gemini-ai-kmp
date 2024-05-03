package data.repository

import data.request.ContentRequestBody
import data.response.GeminiResponse
import di.network.GeminiApi
import domain.interactor.GeminiRepository

class GeminiRepositoryImpl constructor(
    private val api: GeminiApi
) : GeminiRepository {

    override suspend fun request(content: String): GeminiResponse {
        val request = ContentRequestBody.createTextOnlyRequest(content)
        return api.generateContent(request, "api-key")
    }
}