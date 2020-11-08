package com.tkpd.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.tkpd.movieapp.data.movielist.MovieCacheDataStore
import com.tkpd.movieapp.data.movielist.MovieDetailRemoteDataStore
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.performDataStrategy

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailRepository(private val remoteDataSource: MovieDetailRemoteDataStore,
                            private val localDataSourceImpl: MovieCacheDataStore) {

    fun getMovieDetail(movieId: Int): LiveData<Result<MovieDetail?>> = performDataStrategy(
        databaseQuery = { localDataSourceImpl.getCacheMovieDetail(movieId) },
        networkCall = { remoteDataSource.getMovieDetailFromApi(movieId) },
        saveToDb = { localDataSourceImpl.insertMovieDetail(it) }
    )
}