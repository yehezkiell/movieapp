package com.tkpd.movielist.repository

import com.google.gson.Gson
import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.getData
import com.tkpd.abstraction.network.MovieAPI
import com.tkpd.movielist.model.PopularMovies
import javax.inject.Inject

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val gson: Gson
) : MovieListRepository {
    override suspend fun getMovieListFromAPI(): Result<PopularMovies> {
        return getData(
            gson, apiCall = {
                movieAPI.getTopRatedMovies(
                    MovieConstant.DEFAULT_PAGE_PARAM,
                    MovieConstant.DEFAULT_LANGUAGE_PARAM
                )
            },
            PopularMovies::class.java
        )
    }
}