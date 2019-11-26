package com.robertsproats.movies.features.detail

import androidx.lifecycle.*
import com.robertsproats.domain.usecase.feature.GetMovieDetailUseCase
import com.robertsproats.movies.boundary.MoviesPresentationMapper
import com.robertsproats.movies.model.MovieDetailPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val moviesPresentationMapper: MoviesPresentationMapper,
                                               private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private var liveDataResult = MutableLiveData<MovieDetailPresentationModel>()

    val movieDetailLiveData: LiveData<MovieDetailPresentationModel>
        get() = liveDataResult

    fun fetchMovieDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailUseCase.execute(id).map {
                moviesPresentationMapper.mapMovieDetailData(it)
            }.collect {
                liveDataResult.postValue(it)
            }
        }
    }


}