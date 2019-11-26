package com.robertsproats.domain.usecase.feature

import com.robertsproats.domain.model.DomainMoviesModel
import com.robertsproats.domain.repository.DomainMoviesRepository
import com.robertsproats.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val domainMoviesRepository: DomainMoviesRepository) : UseCase<Void, Flow<DomainMoviesModel>>() {

    override suspend fun execute(parameters: Void?): Flow<DomainMoviesModel> {
        return domainMoviesRepository.getPopularMovies()
    }

}