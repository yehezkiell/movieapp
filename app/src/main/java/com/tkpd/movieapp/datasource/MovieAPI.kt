package com.tkpd.movieapp.datasource

import com.tkpd.movieapp.model.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Yehezkiel on 10/05/20
 *
 * Retorfit Documentation : https://square.github.io/retrofit/
 */
interface MovieAPI {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<MovieDetail>
}