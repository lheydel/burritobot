package com.burritobot.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class TenorClient(
    private val httpClient: HttpClient,
    private val apiKey: String
) {
    suspend fun searchGif(query: String, limit: Int = 50): String? {
        return try {
            val response = httpClient.get("https://api.tenor.com/v1/search") {
                parameter("q", query)
                parameter("key", apiKey)
                parameter("limit", limit)
            }
            val result = response.body<TenorResponse>()
            result.results.randomOrNull()?.itemUrl
        } catch (e: Exception) {
            null
        }
    }
}

@Serializable
data class TenorResponse(
    val results: List<TenorResult>
)

@Serializable
data class TenorResult(
    @SerialName("itemurl")
    val itemUrl: String
)
