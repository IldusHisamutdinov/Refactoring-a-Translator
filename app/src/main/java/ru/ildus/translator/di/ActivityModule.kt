package ru.ildus.translator.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.ildus.translator.view.main.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
