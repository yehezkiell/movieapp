package com.tkpd.movieapp.data.movielist

import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result

interface MovieDetailRemoteDataStore {
    suspend fun getMovieDetailFromApi(movieId: Int): Result<MovieDetail>
}