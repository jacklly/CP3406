package com.packt.chapterseven

import android.app.Application
import com.packt.chapterseven.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChapterSevenApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ChapterSevenApplication)
            modules(appModules)
        }
    }
}