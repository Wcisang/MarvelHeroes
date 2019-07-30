package com.wcisang.marvelheroes

import android.app.Application
import com.wcisang.marvelheroes.di.appComponent
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appComponent())}
    }
}