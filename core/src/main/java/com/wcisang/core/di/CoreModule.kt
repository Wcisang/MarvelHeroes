package com.wcisang.core.di

import com.wcisang.core.data.repository.MarvelRepository
import com.wcisang.core.data.repository.MarvelRepositoryImpl
import com.wcisang.core.data.source.MarvelService
import com.wcisang.networking.builder.ServiceBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule: List<Module>
    get() = listOf(serviceModule, repositoryModule)

private val serviceModule = module {
    single { ServiceBuilder.createService(MarvelService::class.java) }
}

private val repositoryModule = module {
    single<MarvelRepository> { MarvelRepositoryImpl(get()) }
}