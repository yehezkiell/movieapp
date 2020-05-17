package com.tkpd.movieapp.util

import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.model.TopRatedMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 10/05/20
 */
interface MovieAPI {
    @GET("movie/popular")
    suspend fun getTopRatedMovies(@Query("page") page: Int,
                                  @Query("language") language: String): Response<TopRatedMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<MovieDetail>
}