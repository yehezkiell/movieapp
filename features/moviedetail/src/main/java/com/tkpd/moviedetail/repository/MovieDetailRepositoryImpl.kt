package com.tkpd.moviedetail.repository

import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.stateCall
import com.tkpd.abstraction.network.MovieAPI
import javax.inject.Inject

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailRepositoryImpl @Inject constructor(private val movieAPI: MovieAPI) :
        MovieDetailRepository {

    override suspend fun getMovieDetailFromAPI(movieId: Int): Result<MovieDetail>? {
        val data = movieAPI.getMovieDetail(movieId)
        return stateCall {
            data
        }
    }
}