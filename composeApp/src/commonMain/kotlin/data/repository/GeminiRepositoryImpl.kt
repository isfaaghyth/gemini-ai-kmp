package data.repository

import data.request.ContentRequestBody
import data.response.GeminiResponse
import di.network.GeminiApi
import domain.interactor.GeminiRepository

class GeminiRepositoryImpl(
    private val api: GeminiApi
) : GeminiRepository {

    override suspend fun request(content: String): GeminiResponse {
        return api.generateContent(
            ContentRequestBody.createTextOnlyRequest(content)
        )
    }

    override suspend fun requestWithImage(content: String, image: ByteArray): GeminiResponse {
        return api.generateVisionContent(
            ContentRequestBody.createTextAndImageAttachmentRequest(content, image)
        )
    }
}