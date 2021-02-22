package ru.ildus.translator.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.ildus.translator.di.application
import ru.ildus.translator.di.mainScreen


class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}


