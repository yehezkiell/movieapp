package com.tkpd.abstraction.network

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.data.PopularMovies
import com.tkpd.abstraction.data.account.AccountDetail
import com.tkpd.abstraction.data.account.SessionData
import com.tkpd.abstraction.data.account.SessionRequestToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yehezkiel on 10/05/20
 */
interface MovieAPI {
    @GET("movie/popular")
    suspend fun getTopRatedMovies(
        @Query(MovieConstant.KEY_PAGE) page: Int,
        @Query(MovieConstant.KEY_LANGUAGE) language: String
    ): Response<PopularMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path(MovieConstant.KEY_MOVIE_ID) movieId: Int): Response<MovieDetail>

    @GET("/3/authentication/token/new")
    suspend fun requestNewToken(): Response<SessionRequestToken>

    @POST("/3/authentication/token/validate_with_login")
    suspend fun createRequestTokenWithLogin(
        @Query(MovieConstant.KEY_USERNAME) userName: String,
        @Query(MovieConstant.KEY_PASSWORD) password: String,
        @Query(MovieConstant.KEY_REQUEST_TOKEN) requestToken: String
    ): Response<SessionRequestToken>

    @POST("/3/authentication/session/new")
    suspend fun createSessionWithRequestToken(
        @Query(MovieConstant.KEY_REQUEST_TOKEN) requestToken: String
    ): Response<SessionData>

    @GET("/3/account")
    suspend fun getDetailAccount(
        @Query(MovieConstant.KEY_SESSION_ID) sessionId: String
    ): Response<AccountDetail>

}