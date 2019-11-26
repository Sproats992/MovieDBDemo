package com.robertsproats.repository.services.retrofit

import com.robertsproats.repository.utils.RepositoryConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class OkHttpInterceptor @Inject constructor() {

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", RepositoryConstants.TMDB_API_KEY)
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

}