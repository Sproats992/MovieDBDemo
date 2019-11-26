package com.robertsproats.repository.movies

import com.robertsproats.domain.model.DomainMovieDetailModel
import com.robertsproats.domain.model.DomainMoviesModel
import com.robertsproats.domain.repository.DomainMoviesRepository
import com.robertsproats.repository.Repository
import com.robertsproats.repository.boundary.MoviesModelMapper
import com.robertsproats.repository.services.Status
import com.robertsproats.repository.services.TmdbClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TmdbMoviesRepository @Inject constructor(
        private val tmdbClient: TmdbClient,
        private val moviesModelMapper: MoviesModelMapper) : Repository(), DomainMoviesRepository {

    override fun getPopularMovies(): Flow<DomainMoviesModel> {

        checkThread("getPopularMovies")

        val returnFlow: Flow<DomainMoviesModel> = flow {
            val movieResponse = tmdbClient.getPopularMovies()
            movieResponse?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.apply {
                            emit(moviesModelMapper.mapMovies(this))
                        }
                    }
                    Status.ERROR -> {
                        Timber.e("Get Popular Movies Error.")
                    }
                    Status.LOADING -> {
                        Timber.e("Get Popular Movies Loading.")
                    }
                }
            }
        }

        return returnFlow
    }

    override fun getMovieDetails(id: String): Flow<DomainMovieDetailModel> {

        checkThread("getMovieDetails")

        val returnFlow: Flow<DomainMovieDetailModel> = flow {
            val movieDetailResponse = tmdbClient.getMovieById(id)
            movieDetailResponse?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.apply {
                            emit(moviesModelMapper.mapMovieDetail(this))
                        }
                    }
                    Status.ERROR -> {
                        Timber.e("Get Movies Details Error.")
                    }
                    Status.LOADING -> {
                        Timber.e("Get Movies Details Loading.")
                    }
                }
            }
        }

        return returnFlow
    }

}