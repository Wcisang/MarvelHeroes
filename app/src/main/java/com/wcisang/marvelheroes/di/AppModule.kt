package com.wcisang.marvelheroes.di

import com.wcisang.marvelheroes.ui.MainViewModel
import com.wcisang.marvelheroes.usecase.GetCharactersUseCase
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
