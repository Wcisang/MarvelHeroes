package com.wcisang.home.di

import com.wcisang.home.ui.MainViewModel
import com.wcisang.home.usecase.GetCharactersUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: List<Module>
    get() = listOf(viewModelModule, useCaseModule)

private val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

private val useCaseModule = module {
    factory { GetCharactersUseCase(get()) }
}
