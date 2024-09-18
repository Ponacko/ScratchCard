package com.example.scratchcard.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.model.ScratchCard
import kotlinx.coroutines.launch

class ScratchViewModel(
    private val cardScratcher: CodeScratcher
) : ViewModel() {

    var scratchCard by mutableStateOf(ScratchCard())
        private set
    var isScratching by mutableStateOf(false)
        private set

    fun scratch() {
        viewModelScope.launch {
            isScratching = true
            try {
                cardScratcher.scratch(scratchCard)
            } finally {
                isScratching = false
            }
        }
    }
}
