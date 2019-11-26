package com.robertsproats.movies.di

import com.robertsproats.movies.features.detail.MovieDetailFragment
import com.robertsproats.movies.features.master.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun provideMovieDetailFragment(): MovieDetailFragment

}