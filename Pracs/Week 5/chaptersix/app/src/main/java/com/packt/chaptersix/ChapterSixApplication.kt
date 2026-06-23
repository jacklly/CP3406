package com.packt.chaptersix

import android.app.Application
import com.packt.chaptersix.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChapterSixApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChapterSixApplication)
            modules(appModules)
        }
    }
}