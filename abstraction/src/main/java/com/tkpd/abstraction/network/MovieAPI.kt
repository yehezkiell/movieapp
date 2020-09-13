package com.tkpd.abstraction.network

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.data.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 10/05/20
 */
interface MovieAPI {
    @GET("movie/popular")
    suspend fun getTopRatedMovies(@Query(MovieConstant.KEY_PAGE) page: Int,
                                  @Query(MovieConstant.KEY_LANGUAGE) language: String): Response<PopularMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path(MovieConstant.KEY_MOVIE_ID) movieId: Int): Response<MovieDetail>
}