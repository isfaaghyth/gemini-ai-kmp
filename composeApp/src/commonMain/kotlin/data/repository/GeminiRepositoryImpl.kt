package data.repository

import data.request.ContentRequestBody
import data.response.GeminiResponse
import di.network.GeminiApi
import domain.interactor.GeminiRepository

class GeminiRepositoryImpl(
    private val api: GeminiApi
) : GeminiRepository {

    override suspend fun request(content: String): GeminiResponse {
        val request = ContentRequestBody.createTextOnlyRequest(content)
        return api.generateContent(request, "api-key")
    }

    override suspend fun requestWithImage(content: String, image: ByteArray): GeminiResponse {
        val request = ContentRequestBody.createTextAndImageAttachmentRequest(content, image)
        return api.generateVisionContent(request, "api-key")
    }
}