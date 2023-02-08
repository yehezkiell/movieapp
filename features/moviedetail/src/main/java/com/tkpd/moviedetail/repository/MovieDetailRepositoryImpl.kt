package com.tkpd.moviedetail.repository

import com.google.gson.Gson
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.getData
import com.tkpd.abstraction.network.MovieAPI
import com.tkpd.moviedetail.model.MovieDetail
import javax.inject.Inject

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val gson: Gson
) : MovieDetailRepository {

    override suspend fun getMovieDetailFromAPI(movieId: Int): Result<MovieDetail> {
        return getData(
            gson, apiCall = {
                movieAPI.getMovieDetail(movieId)
            },
            MovieDetail::class.java
        )
    }
}