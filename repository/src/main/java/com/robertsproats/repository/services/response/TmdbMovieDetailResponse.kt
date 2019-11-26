package com.robertsproats.repository.services.response

data class TmdbMovieDetailResponse(
        val vote_average: Double?,
        val title: String?,
        val overview: String?,
        val adult: Boolean?,
        val poster_path: String?,
        val backdrop_path: String?,
        val original_language: String?,
        val release_date: String?
)
