package com.tkpd.movieapp.datasource.repository

import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.RetrofitInstanceBuilder
import com.tkpd.movieapp.util.stateCall

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListRepositoryImpl : MovieListRepository {
    override suspend fun getMovieListFromAPI(): Result<PopularMovies>? {
        val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.getTopRatedMovies(
            MovieConstant.DEFAULT_PAGE_PARAM,
            MovieConstant.DEFAULT_LANGUAGE_PARAM
        )
        return stateCall {
            data
        }
    }
}