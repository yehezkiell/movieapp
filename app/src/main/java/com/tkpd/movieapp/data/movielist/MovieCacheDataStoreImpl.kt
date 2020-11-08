package com.tkpd.movieapp.data.movielist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.tkpd.movieapp.database.MovieDatabase
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.MovieDetailEntity
import com.tkpd.movieapp.model.MovieListEntity
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import com.tkpd.movieapp.util.fromJson
import com.tkpd.movieapp.util.toJson

/**
 * Created by Yehezkiel on 11/10/20
 */
class MovieCacheDataStoreImpl(context: Context) : MovieCacheDataStore {

    private val movieDao = MovieDatabase.getDatabase(context).movieDao()

    override fun getCacheMovieList(): LiveData<Result<PopularMovies?>> {
        return movieDao.getMovieList().map {
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

    override fun getCacheMovieDetail(movieId: Int): LiveData<Result<MovieDetail?>> {
        return movieDao.getMovieDetail(movieId).map {
            try {
                //Since we're store the popular movies by string, we need to convert back to pojo
                val value: MovieDetail? = it?.value?.fromJson()
                Result.Success(value)
            } catch (e: NullPointerException) {
                // If the databases still empty, perform loading.Then it can be more seamless to users
                Result.Loading
            }
        }
    }

    override suspend fun insertMovieDetail(movieDetail: MovieDetail?) {
        movieDetail?.run {
            movieDao.insertMovieDetail(MovieDetailEntity(movieId = id, value = this.toJson()))
        }
    }

    override suspend fun insertMovieList(popularMovies: PopularMovies?) {
        popularMovies?.run {
            movieDao.insertMovieList(MovieListEntity(key = 1, value = popularMovies.toJson()))
        }
    }
}