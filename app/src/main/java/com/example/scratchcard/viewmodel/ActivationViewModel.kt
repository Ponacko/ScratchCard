package com.example.scratchcard.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.network.CodeActivator
import kotlinx.coroutines.launch

class ActivationViewModel(
    private val scratchCard: ScratchCard,
    private val codeActivator: CodeActivator,
) : ViewModel() {

    var isActivating by mutableStateOf(false)
        private set

    var activationError by mutableStateOf<String?>(null)
        private set

    var canBeActivated: Boolean
        get() = codeActivator.canBeActivated(scratchCard)
        private set(_) {}

    var isAlreadyActivated: Boolean
        get() = codeActivator.isAlreadyActivated(scratchCard)
        private set(_) {}

    fun activate() {
        viewModelScope.launch {
            isActivating = true
            activationError = null
            try {
                codeActivator.activate(scratchCard)
            } catch (e: Exception) {
                activationError = "An error occurred: ${e.message}"
            } finally {
                isActivating = false
            }
        }
    }

    fun clearError() {
        activationError = null
    }
}
