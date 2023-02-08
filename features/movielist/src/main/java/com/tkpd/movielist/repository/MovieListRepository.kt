package com.tkpd.movielist.repository

import com.tkpd.abstraction.extension.Result
import com.tkpd.movielist.model.PopularMovies

/**
 * Created by Yehezkiel on 11/06/20
 */
interface MovieListRepository {
    suspend fun getMovieListFromAPI(): Result<PopularMovies>
}