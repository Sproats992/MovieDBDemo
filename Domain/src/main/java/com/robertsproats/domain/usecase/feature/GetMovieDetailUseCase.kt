package com.robertsproats.domain.usecase.feature

import com.robertsproats.domain.model.DomainMovieDetailModel
import com.robertsproats.domain.repository.DomainMoviesRepository
import com.robertsproats.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val domainMoviesRepository: DomainMoviesRepository) : UseCase<String, Flow<DomainMovieDetailModel?>>() {

    override suspend fun execute(parameters: String?): Flow<DomainMovieDetailModel?> {
        return parameters?.let {
            domainMoviesRepository.getMovieDetails(it)
        } ?: run {
            val returnFlow: Flow<DomainMovieDetailModel?> = flow {
                emit(null)
            }
            returnFlow
        }
    }
}