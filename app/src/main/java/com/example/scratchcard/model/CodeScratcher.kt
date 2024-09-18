package com.example.scratchcard.model

import kotlinx.coroutines.delay
import java.util.UUID

class CodeScratcher {

    suspend fun scratch(card: ScratchCard) {
        // Simulate a heavy operation with a 2-second delay
        delay(2000)
        val code = UUID.randomUUID().toString()
        card.setCardScratched(code)
    }
}