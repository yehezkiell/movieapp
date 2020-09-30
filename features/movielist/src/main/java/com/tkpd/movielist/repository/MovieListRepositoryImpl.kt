package com.tkpd.movielist.repository

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.data.PopularMovies
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.stateCall
import com.tkpd.abstraction.network.MovieAPI
import javax.inject.Inject

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListRepositoryImpl @Inject constructor(private val movieAPI: MovieAPI) : MovieListRepository {
    override suspend fun getMovieListFromAPI(): Result<PopularMovies>? {
        val data = movieAPI.getTopRatedMovies(
            MovieConstant.DEFAULT_PAGE_PARAM,
            MovieConstant.DEFAULT_LANGUAGE_PARAM
        )
        return stateCall {
            data
        }
    }
}