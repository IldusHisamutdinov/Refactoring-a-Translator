package ru.ildus.translator.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.ildus.translator.di.application
import ru.ildus.translator.di.historyScreen
import ru.ildus.translator.di.mainScreen


class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}


