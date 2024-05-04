package data.request

import io.ktor.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ContentRequestBody(
    @SerialName("contents") val contents: List<RequestContentItem>
) {

    companion object {
        /**
         * This request is experimental version.
         *
         * As we see, the request part resetting every single request got made,
         * hence the gemini couldn't memorize the historical previous request.
         * Thus, the gemini wouldn't hold the previous context.
         */
        fun createTextOnlyRequest(text: String): String {
            val body = ContentRequestBody(
                contents = listOf(
                    RequestContentItem(
                        listOf(
                            RequestContentPart(
                                text = text
                            )
                        )
                    )
                )
            )

            return Json.encodeToString(body)
        }

        fun createTextAndImageAttachmentRequest(text: String, image: ByteArray): String {
            val parts = mutableListOf<RequestContentPart>()
            // command
            parts.add(RequestContentPart(text = text))
            
            // attachment
            parts.add(
                RequestContentPart(
                    data = RequestInlineData(
                        mimeType = "image/jpeg",
                        data = image.encodeBase64()
                    )
                )
            )

            val body = ContentRequestBody(contents = listOf(RequestContentItem(parts)))
            return Json.encodeToString(body)
        }
    }
}
