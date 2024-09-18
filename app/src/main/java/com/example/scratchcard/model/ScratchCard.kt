package com.example.scratchcard.model

class ScratchCard(
    private var _code: String = "",
    private var _state: ScratchCardState = ScratchCardState.UNSCRATCHED,
) {

    val code: String
        get() = _code

    val state: ScratchCardState
        get() = _state

    fun setCardScratched(code: String) {
        if (_state == ScratchCardState.UNSCRATCHED) {
            _code = code
            _state = ScratchCardState.SCRATCHED
        }
    }

    fun activateCard() {
        if (_state == ScratchCardState.SCRATCHED) {
            _state = ScratchCardState.ACTIVATED
        }
    }
}
