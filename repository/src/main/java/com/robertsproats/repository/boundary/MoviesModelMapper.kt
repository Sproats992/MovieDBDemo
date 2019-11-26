package com.robertsproats.repository.boundary

import com.robertsproats.domain.model.DomainMovieDetailModel
import com.robertsproats.domain.model.DomainMovieItemModel
import com.robertsproats.domain.model.DomainMoviesModel
import com.robertsproats.repository.services.response.TmdbMovie
import com.robertsproats.repository.services.response.TmdbMovieDetailResponse
import com.robertsproats.repository.services.response.TmdbMovieResponse
import com.robertsproats.repository.utils.RepositoryConstants

class MoviesModelMapper {

    fun mapMovies(tmdbMovies: TmdbMovieResponse): DomainMoviesModel {
        return tmdbMovies.results?.let {
            DomainMoviesModel(movies = Array(it.size) {
                mapMovieItem(tmdbMovies.results[it])
            })
        } ?: run {
            DomainMoviesModel(movies = null)
        }
    }

    fun mapMovieDetail(tmdbMovieDetail: TmdbMovieDetailResponse): DomainMovieDetailModel {
        return DomainMovieDetailModel(
                voteAverage = tmdbMovieDetail.vote_average ?: 0.0,
                title = tmdbMovieDetail.title ?: "",
                overview = tmdbMovieDetail.overview ?: "",
                adult = tmdbMovieDetail.adult ?: true,
                posterPath = constructURLFilePath(tmdbMovieDetail.poster_path),
                backdropPath = constructURLFilePath(tmdbMovieDetail.backdrop_path),
                originalLanguage = tmdbMovieDetail.original_language ?: "",
                releaseDate = tmdbMovieDetail.release_date ?: ""
        )
    }

    private fun mapMovieItem(movie: TmdbMovie): DomainMovieItemModel {
        return DomainMovieItemModel(
                id = movie.id ?: 0,
                voteAverage = movie.vote_average ?: 0.0,
                title = movie.title ?: "",
                overview = movie.overview ?: "",
                adult = movie.adult ?: true,
                posterPath = constructURLFilePath(movie.poster_path),
                backdropPath = constructURLFilePath(movie.backdrop_path),
                originalLanguage = movie.original_language ?: "",
                releaseDate = movie.release_date ?: ""
        )
    }

    private fun constructURLFilePath(file: String?): String {
        return file?.let {
            "${RepositoryConstants.FILE_URL}$file"
        } ?: run {
            ""
        }
    }
}