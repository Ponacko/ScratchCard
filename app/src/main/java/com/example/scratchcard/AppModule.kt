package com.example.scratchcard

import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.ui.ScratchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CodeScratcher() }
    viewModel { ScratchViewModel(get()) }
}