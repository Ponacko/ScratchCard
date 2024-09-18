package com.example.scratchcard

import com.example.scratchcard.model.CodeScratcher
import com.example.scratchcard.model.ScratchCard
import com.example.scratchcard.network.ApiService
import com.example.scratchcard.network.CodeActivator
import com.example.scratchcard.network.KtorClientProvider
import com.example.scratchcard.viewmodel.ActivationViewModel
import com.example.scratchcard.viewmodel.ScratchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ScratchCard() }
    single { KtorClientProvider.client }
    single { CodeScratcher() }
    single { ApiService(get()) }
    single { CodeActivator(get()) }
    viewModel { ScratchViewModel(get(), get()) }
    viewModel { ActivationViewModel(get(), get()) }
}