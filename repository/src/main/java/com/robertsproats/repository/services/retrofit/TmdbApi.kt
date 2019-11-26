package com.robertsproats.repository.services.retrofit

import com.robertsproats.repository.services.response.TmdbMovieDetailResponse
import com.robertsproats.repository.services.response.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): TmdbMovieResponse

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: String): TmdbMovieDetailResponse
}