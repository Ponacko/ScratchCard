package com.example.scratchcard.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    suspend fun activateCard(code: String): Map<String, String> {
        val url = "https://api.o2.sk/version?code=$code"
        return client.get(url).body()
    }
}