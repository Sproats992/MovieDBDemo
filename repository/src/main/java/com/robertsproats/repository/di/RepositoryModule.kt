package com.robertsproats.repository.di

import com.robertsproats.domain.repository.DomainMoviesRepository
import com.robertsproats.repository.boundary.MoviesModelMapper
import com.robertsproats.repository.movies.TmdbMoviesRepository
import com.robertsproats.repository.services.ResponseHandler
import com.robertsproats.repository.services.TmdbClient
import com.robertsproats.repository.services.retrofit.OkHttpInterceptor
import com.robertsproats.repository.services.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    // repositories
    @Provides
    @Singleton
    fun providesDomainNewsRepository(
            tmdbClient: TmdbClient,
            moviesModelMapper: MoviesModelMapper
    ): DomainMoviesRepository = TmdbMoviesRepository(
            tmdbClient = tmdbClient,
            moviesModelMapper = moviesModelMapper
    )

    @Provides
    @Singleton
    fun providesTmdbClient(): TmdbClient = RetrofitClient(
            okHttpInterceptor = OkHttpInterceptor(),
            responseHandler = ResponseHandler()
    )

    @Provides
    @Singleton
    fun providesMoviesModelMapper(): MoviesModelMapper = MoviesModelMapper()

}