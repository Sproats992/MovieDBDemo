package com.robertsproats.domain.model

data class DomainMovieDetailModel(
        val voteAverage: Double,
        val title: String,
        val overview: String,
        val adult: Boolean,
        val posterPath: String,
        val backdropPath: String,
        val originalLanguage: String,
        val releaseDate: String
)