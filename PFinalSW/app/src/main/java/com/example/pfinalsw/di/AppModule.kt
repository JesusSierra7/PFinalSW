package com.example.pfinalsw.di

import com.example.pfinalsw.presentation.viewmodel.VMMain
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

        viewModel { VMMain(get()) }

        single { createDataRepository(get()) }

        single { createGetDataUseCase(get()) }
}

