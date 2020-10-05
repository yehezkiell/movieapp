package com.tkpd.moviedetail.repository

import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.Result
import com.tkpd.moviedetail.di.MovieDetailScope

/**
 * Created by Yehezkiel on 11/06/20
 */
interface MovieDetailRepository {
    suspend fun getMovieDetailFromAPI(movieId: Int): Result<MovieDetail>?
}