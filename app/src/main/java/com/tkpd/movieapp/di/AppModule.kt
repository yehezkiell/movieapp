package com.tkpd.movieapp.di

import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.datasource.MovieAPI
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Yehezkiel on 30/05/20
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun retrofitClient(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(MovieConstant.MOVIE_BASE_URL)
            .client(RetrofitInstanceBuilder.okHttpClientInstance)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }
}