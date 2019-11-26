package com.robertsproats.moviedbtest.di

import com.robertsproats.domain.repository.DomainMoviesRepository
import com.robertsproats.domain.usecase.feature.GetMoviesUseCase
import com.robertsproats.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class DomainModule {

    // use cases
    @Provides
    @Singleton
    fun providesGetMoviesUseCase(moviesRepository: DomainMoviesRepository): GetMoviesUseCase = GetMoviesUseCase(
            domainMoviesRepository = moviesRepository
    )


}