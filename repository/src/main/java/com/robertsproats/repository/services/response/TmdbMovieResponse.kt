package com.robertsproats.repository.services.response

// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
        val results: List<TmdbMovie>?
)

// Data Model for TMDB Movie item
data class TmdbMovie(
        val id: Int?,
        val vote_average: Double?,
        val title: String?,
        val overview: String?,
        val adult: Boolean?,
        val poster_path: String?,
        val backdrop_path: String?,
        val original_language: String?,
        val release_date: String?
)
