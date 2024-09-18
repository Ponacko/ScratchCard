package com.example.scratchcard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.model.ScratchCard
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScratchViewModel(
    val scratchCard: ScratchCard,
    private val cardScratcher: CodeScratcher
) : ViewModel() {

    var isScratching by mutableStateOf(false)
        private set

    private var scratchJob: Job? = null

    fun scratch() {
        scratchJob?.cancel()

        scratchJob = viewModelScope.launch {
            isScratching = true
            try {
                cardScratcher.scratch(scratchCard)
            } finally {
                isScratching = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelScratching()
    }

    fun cancelScratching() {
        scratchJob?.cancel()
    }

}
