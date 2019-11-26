package com.robertsproats.movies.boundary

import com.robertsproats.domain.model.DomainMovieDetailModel
import com.robertsproats.domain.model.DomainMovieItemModel
import com.robertsproats.domain.model.DomainMoviesModel
import com.robertsproats.movies.model.MovieDetailPresentationModel
import com.robertsproats.movies.model.MoviePresentationItemModel
import com.robertsproats.movies.model.MoviesPresentationModel
import javax.inject.Inject

class MoviesPresentationMapper @Inject constructor() {

    fun mapMoviesData(domainMovies: DomainMoviesModel): MoviesPresentationModel {

        val crossModuleArray = domainMovies.movies

        return crossModuleArray?.let {
            MoviesPresentationModel(moviesList = Array(it.size) {
                mapMovieDataItem(crossModuleArray[it])
            })
        } ?: run {
            MoviesPresentationModel(moviesList = null)
        }
    }

    fun mapMovieDetailData(domainMovie: DomainMovieDetailModel?): MovieDetailPresentationModel? {

        val crossModuleModel = domainMovie

        return crossModuleModel?.let {
            MovieDetailPresentationModel(
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath
            )
        } ?: run {
            null
        }
    }

    private fun mapMovieDataItem(domainItem: DomainMovieItemModel): MoviePresentationItemModel {
        return MoviePresentationItemModel(
                id = domainItem.id,
                voteAverage = domainItem.voteAverage,
                title = domainItem.title,
                overview = domainItem.overview,
                backdropPath = domainItem.backdropPath,
                posterPath = domainItem.posterPath,
                releaseDate = domainItem.releaseDate
        )
    }

}