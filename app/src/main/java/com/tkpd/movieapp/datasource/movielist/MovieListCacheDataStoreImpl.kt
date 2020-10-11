package com.tkpd.movieapp.datasource.movielist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.tkpd.movieapp.database.MovieDatabase
import com.tkpd.movieapp.model.MovieListEntity
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.fromJson
import com.tkpd.movieapp.util.toJson

/**
 * Created by Yehezkiel on 11/10/20
 */
class MovieListCacheDataStoreImpl(context: Context) : MovieListCacheDataStore {

    private val movieDao = MovieDatabase.getDatabase(context).movieDao()

    override fun getCacheMovieList(): LiveData<Result<PopularMovies?>> {
        return movieDao.getAllCharacters().map {
            try {
                //Since we're store the popular movies by string, we need to convert back to pojo
                val value: PopularMovies? = it?.value?.fromJson()
                Result.Success(value)
            } catch (e: NullPointerException) {
                // If the databases still empty, perform loading.Then it can be more seamless to users
                Result.Loading
            }
        }
    }

    override suspend fun insertAll(popularMovies: PopularMovies?) {
        popularMovies?.run {
            movieDao.insertAll(MovieListEntity(key = 1, value = popularMovies.toJson()))
        }
    }
}