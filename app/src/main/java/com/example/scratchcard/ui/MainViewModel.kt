package com.example.scratchcard.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.scratchcard.model.ScratchCard

class MainViewModel : ViewModel() {

    var scratchCard by mutableStateOf(ScratchCard())
}
