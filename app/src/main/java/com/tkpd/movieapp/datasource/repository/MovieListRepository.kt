package com.tkpd.movieapp.datasource.repository

import com.tkpd.movieapp.model.TopRatedMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import com.tkpd.movieapp.util.stateCall
import retrofit2.Response

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListRepository() {
    suspend fun getMovieListFromAPI(): Result<TopRatedMovies>? {
        val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.getTopRatedMovies(1, "en-US")
        return stateCall {
            data
        }
    }
}