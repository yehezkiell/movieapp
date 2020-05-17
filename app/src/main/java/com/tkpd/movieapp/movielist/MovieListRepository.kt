package com.tkpd.movieapp.movielist

import com.tkpd.movieapp.model.TopRatedMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
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

    private suspend fun <T : Any> stateCall(call: suspend () -> Response<T>): Result<T> {
        return if (call.invoke().isSuccessful) {
            Result.Success(call.invoke().body()!!)
        } else {
            Result.Error(Throwable(call.invoke().message()))
        }
    }

}