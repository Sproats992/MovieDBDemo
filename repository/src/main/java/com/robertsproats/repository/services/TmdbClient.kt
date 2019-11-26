package com.robertsproats.repository.services

import com.robertsproats.repository.services.response.TmdbMovieDetailResponse
import com.robertsproats.repository.services.response.TmdbMovieResponse

interface TmdbClient {

    suspend fun getPopularMovies() : Resource<TmdbMovieResponse>

    suspend fun getMovieById(id: String) : Resource<TmdbMovieDetailResponse>

}