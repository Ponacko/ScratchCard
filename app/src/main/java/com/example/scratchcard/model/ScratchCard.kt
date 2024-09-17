package com.example.scratchcard.model

import java.util.UUID

class ScratchCard(
    private var _code: String = "",
    private var _state: ScratchCardState = ScratchCardState.UNSCRATCHED,
) {

    val code: String
        get() = _code

    val state: ScratchCardState
        get() = _state

    fun scratchCard() {
        if (_state == ScratchCardState.UNSCRATCHED) {
            _code = UUID.randomUUID().toString()
            _state = ScratchCardState.SCRATCHED
        }
    }

    fun activateCard() {
        if (_state == ScratchCardState.SCRATCHED) {
            _state = ScratchCardState.ACTIVATED
        }
    }
}
