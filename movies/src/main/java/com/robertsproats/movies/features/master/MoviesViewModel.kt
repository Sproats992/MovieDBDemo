package com.robertsproats.movies.features.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertsproats.domain.usecase.feature.GetMoviesUseCase
import com.robertsproats.movies.boundary.MoviesPresentationMapper
import com.robertsproats.movies.model.MoviesPresentationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesPresentationMapper: MoviesPresentationMapper,
                                          private val getMoviesUseCase: GetMoviesUseCase,
                                          private val defaultDispatcher: CoroutineDispatcher) : ViewModel() {

    private var liveDataResult = MutableLiveData<MoviesPresentationModel>()

    val moviesLiveData: LiveData<MoviesPresentationModel>
        get() = liveDataResult

    fun fetchMovies() {
        viewModelScope.launch(defaultDispatcher) {
            getMoviesUseCase.execute(null)
                    .map {
                        moviesPresentationMapper.mapMoviesData(it)
                    }.collect {
                        liveDataResult.postValue(it)
                    }
        }
    }

}
