package com.robertsproats.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robertsproats.movies.features.detail.MovieDetailViewModel
import com.robertsproats.movies.features.master.MoviesViewModel
import com.robertsproats.movies.util.ViewModelFactory
import com.robertsproats.movies.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun postMovieModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun postMovieDetailModel(viewModel: MovieDetailViewModel): ViewModel

}