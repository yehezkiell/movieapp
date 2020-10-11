package com.tkpd.movieapp.datasource.movielist

import androidx.lifecycle.LiveData
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result

/**
 * Created by Yehezkiel on 11/10/20
 */
interface MovieListCacheDataStore {
    fun getCacheMovieList(): LiveData<Result<PopularMovies?>>
    suspend fun insertAll(popularMovies: PopularMovies?)
}