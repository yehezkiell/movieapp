package com.tkpd.movieapp.datasource.movielist

import androidx.lifecycle.LiveData
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.performDataStrategy

/**
 * Created by Yehezkiel on 11/10/20
 */
class MovieListRepository(private val remoteDataSource: MovieListRemoteDataSource,
                          private val localDataSourceImpl: MovieListCacheDataStore) {

    fun getPopularMovieList(): LiveData<Result<PopularMovies?>> = performDataStrategy(
        databaseQuery = { localDataSourceImpl.getCacheMovieList() },
        networkCall = { remoteDataSource.getMovieListFromAPI() },
        saveCallResult = { localDataSourceImpl.insertAll(it) }
    )
}