package di.network

import data.response.GeminiResponse
import de.jensklingenberg.ktorfit.http.*

interface GeminiApi {
    
    @POST("v1beta/models/${GEMINI_PRO}:generateContent")
    suspend fun generateContent(@Body request: String, @Query("key") key: String): GeminiResponse
    
    @POST("v1beta/models/${GEMINI_PRO_VISION}:generateContent")
    suspend fun generateVisionContent(@Body request: String, @Query("key") key: String): GeminiResponse

    companion object {
        const val BASE_URL = "https://generativelanguage.googleapis.com/"
        
        private const val GEMINI_PRO = "gemini-pro"
        private const val GEMINI_PRO_VISION = "gemini-pro-vision"
    }
}