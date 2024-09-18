package com.example.scratchcard.network

import com.example.scratchcard.MIN_VALUE_FOR_ACTIVATION
import com.example.scratchcard.PLATFORM_ACTIVATION_FIELD
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.model.ScratchCardState

class CodeActivator(
    private val apiService: ApiService
) {
    suspend fun activate(scratchCard: ScratchCard) {
        if (canBeActivated(scratchCard)) {
            val code = scratchCard.code
            val response = apiService.activateCard(code)
            val androidValue = response[PLATFORM_ACTIVATION_FIELD]?.toIntOrNull() ?: 0
            if (androidValue > MIN_VALUE_FOR_ACTIVATION) {
                scratchCard.setActivated()
            } else {
                throw Exception("Activation failed. Please try again.")
            }
        } else {
            throw Exception("This scratch card does not have a scratched code.")
        }

    }

    fun canBeActivated(scratchCard: ScratchCard) =
        scratchCard.state == ScratchCardState.SCRATCHED && scratchCard.code.isNotEmpty()

    fun isAlreadyActivated(scratchCard: ScratchCard) =
        scratchCard.state == ScratchCardState.ACTIVATED
}