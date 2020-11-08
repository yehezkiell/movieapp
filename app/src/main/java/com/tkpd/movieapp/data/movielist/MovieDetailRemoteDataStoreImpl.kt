package com.tkpd.movieapp.data.movielist

import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import com.tkpd.movieapp.util.stateCall

class MovieDetailRemoteDataStoreImpl : MovieDetailRemoteDataStore {
    override suspend fun getMovieDetailFromApi(movieId: Int): Result<MovieDetail> {
        val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.getMovieDetail(movieId)
        return stateCall {
            data
        }
    }
}