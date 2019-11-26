package com.robertsproats.domain.repository

import com.robertsproats.domain.model.DomainMovieDetailModel
import com.robertsproats.domain.model.DomainMoviesModel
import kotlinx.coroutines.flow.Flow

interface DomainMoviesRepository {

    fun getPopularMovies(): Flow<DomainMoviesModel>

    fun getMovieDetails(id: String): Flow<DomainMovieDetailModel>

}