package com.wcisang.marvelheroes.di

import com.wcisang.core.di.coreModule
import org.koin.core.module.Module

val appComponent = {
    val mutableList = mutableListOf<Module>()
    mutableList.addAll(appModule)
    mutableList.addAll(coreModule)
    mutableList.toList()
}