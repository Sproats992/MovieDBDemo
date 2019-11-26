package com.robertsproats.moviedbtest.di

import com.robertsproats.moviedbtest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PresentationModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}