package com.tkpd.moviedetail.repository

import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.stateCall
import com.tkpd.abstraction.network.RetrofitInstanceBuilder

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailRepositoryImpl : MovieDetailRepository {

    override suspend fun getMovieDetailFromAPI(movieId:Int): Result<MovieDetail>? {
        val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.getMovieDetail(movieId)
        return stateCall {
            data
        }
    }
}