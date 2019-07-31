package com.wcisang.marvelheroes.di

import com.wcisang.core.di.coreModule
import com.wcisang.home.di.appModule
import org.koin.core.module.Module

val appComponent = {
    val mutableList = mutableListOf<Module>()
    mutableList.addAll(appModule)
    mutableList.addAll(coreModule)
    mutableList.toList()
}