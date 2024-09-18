package com.example.scratchcard.model

import com.example.scratchcard.SCRATCH_DELAY_MS
import kotlinx.coroutines.delay
import java.util.UUID

class CodeScratcher {

    suspend fun scratch(card: ScratchCard) {
        // Simulate a heavy operation with a 2-second delay
        delay(SCRATCH_DELAY_MS)
        val code = UUID.randomUUID().toString()
        card.setScratched(code)
    }
}