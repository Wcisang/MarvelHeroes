package com.wcisang.widget.di

import com.wcisang.widget.usecase.GetCharactersWidgetUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val widgetModule: List<Module>
    get() = listOf(useCaseModule)

private val useCaseModule = module {
    factory { GetCharactersWidgetUseCase(get()) }
}
