package com.robertsproats.movies.model

data class MoviesPresentationModel(
        val moviesList: Array<MoviePresentationItemModel>?
)

data class MoviePresentationItemModel(
        val id: Int,
        val voteAverage: Double,
        val title: String,
        val overview: String,
        val backdropPath: String,
        val posterPath: String,
        val releaseDate: String
)