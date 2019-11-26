package com.robertsproats.repository.services.retrofit

import com.google.gson.GsonBuilder
import com.robertsproats.repository.services.Resource
import com.robertsproats.repository.services.ResponseHandler
import com.robertsproats.repository.services.TmdbClient
import com.robertsproats.repository.services.response.TmdbMovieDetailResponse
import com.robertsproats.repository.services.response.TmdbMovieResponse
import com.robertsproats.repository.utils.RepositoryConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(
        private val okHttpInterceptor: OkHttpInterceptor,
        private val responseHandler: ResponseHandler
) : TmdbClient {

    private val webservice by lazy {
        Retrofit.Builder()
                .client(okHttpInterceptor.okHttpClient)
                .baseUrl(RepositoryConstants.TMDB_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(TmdbApi::class.java)
    }

    private var client: TmdbApi = webservice

    private suspend fun getHandledPopularMovies(): Resource<TmdbMovieResponse> {
        return try {
            responseHandler.handleSuccess(client.getPopularMovies())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private suspend fun getHandledMovieById(id: String): Resource<TmdbMovieDetailResponse> {
        return try {
            responseHandler.handleSuccess(client.getMovieById(id))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getPopularMovies() = getHandledPopularMovies()

    override suspend fun getMovieById(id: String) = getHandledMovieById(id)

}