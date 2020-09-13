package com.tkpd.movielist.repository

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.data.PopularMovies

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListRepositoryImpl : MovieListRepository {
    override suspend fun getMovieListFromAPI(): com.tkpd.abstraction.extension.Result<PopularMovies>? {
        val data = com.tkpd.abstraction.network.RetrofitInstanceBuilder.RETROFIT_INSTANCE.getTopRatedMovies(
            MovieConstant.DEFAULT_PAGE_PARAM,
            MovieConstant.DEFAULT_LANGUAGE_PARAM
        )
        return com.tkpd.abstraction.extension.stateCall {
            data
        }
    }
}