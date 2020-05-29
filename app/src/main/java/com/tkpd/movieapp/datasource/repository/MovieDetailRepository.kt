package com.tkpd.movieapp.datasource.repository

import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.TopRatedMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import com.tkpd.movieapp.util.stateCall
import retrofit2.Response

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailRepository() {

    suspend fun getMovieDetailFromAPI(movieId:Int): Result<MovieDetail>? {
        val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.getMovieDetail(movieId)
        return stateCall {
            data
        }
    }
}