package com.robertsproats.movies.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [MoviesFragmentModule::class, MoviesViewModelModule::class])
class MoviesModule {

    @Provides
    @Singleton
    fun providesDefaultCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.Default

}