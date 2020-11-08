package com.tkpd.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tkpd.movieapp.model.MovieDetailEntity
import com.tkpd.movieapp.model.MovieListEntity

/**
 * Created by Yehezkiel on 11/10/20
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movielist")
    fun getMovieList(): LiveData<MovieListEntity?>

    @Query("SELECT * FROM movieDetail WHERE movieId=:movieId")
    fun getMovieDetail(movieId: Int): LiveData<MovieDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieEntity: MovieListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieEntity: MovieDetailEntity)
}