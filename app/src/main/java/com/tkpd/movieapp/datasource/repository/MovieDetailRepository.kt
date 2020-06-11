package com.tkpd.movieapp.datasource.repository

import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 11/06/20
 */
interface MovieDetailRepository {
    suspend fun getMovieDetailFromAPI(movieId: Int): Result<MovieDetail>?
}