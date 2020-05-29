package com.tkpd.movieapp.util

import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.datasource.MovieAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yehezkiel on 10/05/20
 */
object RetrofitInstanceBuilder {

    val RETROFIT_INSTANCE: MovieAPI by lazy {
        Retrofit.Builder()
            .baseUrl(MovieConstant.MOVIE_BASE_URL)
            .client(okHttpClientInstance)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }

    private val okHttpClientInstance: OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        client.addInterceptor(requestInterceptor)
        client.build()
    }

    private val requestInterceptor : Interceptor by lazy {
        RequestInterceptor()
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
}