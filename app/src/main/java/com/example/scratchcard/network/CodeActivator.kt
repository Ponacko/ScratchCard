package com.example.scratchcard.network

import com.example.scratchcard.MIN_VALUE_FOR_ACTIVATION
import com.example.scratchcard.PLATFORM_ACTIVATION_FIELD
import com.example.scratchcard.model.ScratchCard

class CodeActivator(
    private val apiService: ApiService
) {
    suspend fun activate(scratchCard: ScratchCard) {
        val code = scratchCard.code
        val response = apiService.activateCard(code)
        val androidValue = response[PLATFORM_ACTIVATION_FIELD]?.toIntOrNull() ?: 0
        if (androidValue > MIN_VALUE_FOR_ACTIVATION) {
            scratchCard.activateCard()
        } else {
            throw Exception("Activation failed. Please try again.")
        }
    }
}