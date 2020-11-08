package com.tkpd.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.tkpd.movieapp.data.movielist.MovieCacheDataStore
import com.tkpd.movieapp.data.movielist.MovieListRemoteDataSource
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.performDataStrategy

/**
 * Created by Yehezkiel on 11/10/20
 */
class MovieListRepository(private val remoteDataSource: MovieListRemoteDataSource,
                          private val localDataSourceImpl: MovieCacheDataStore) {

    fun getPopularMovieList(): LiveData<Result<PopularMovies?>> = performDataStrategy(
        databaseQuery = { localDataSourceImpl.getCacheMovieList() },
        networkCall = { remoteDataSource.getMovieListFromAPI() },
        saveToDb = { localDataSourceImpl.insertMovieList(it) }
    )
}