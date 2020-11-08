package com.tkpd.movieapp.data.movielist

import androidx.lifecycle.LiveData
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 11/10/20
 */
interface MovieCacheDataStore {
    fun getCacheMovieList(): LiveData<Result<PopularMovies?>>
    fun getCacheMovieDetail(movieId: Int): LiveData<Result<MovieDetail?>>

    suspend fun insertMovieDetail(movieDetail: MovieDetail?)
    suspend fun insertMovieList(popularMovies: PopularMovies?)
}