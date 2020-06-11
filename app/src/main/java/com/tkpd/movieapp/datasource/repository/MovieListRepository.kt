package com.tkpd.movieapp.datasource.repository

import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 11/06/20
 */
interface MovieListRepository {
    suspend fun getMovieListFromAPI(): Result<PopularMovies>?
}