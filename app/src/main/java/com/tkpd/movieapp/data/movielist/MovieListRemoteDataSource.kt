package com.tkpd.movieapp.data.movielist

import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 11/10/20
 */
interface MovieListRemoteDataSource {
    suspend fun getMovieListFromAPI(): Result<PopularMovies>
}