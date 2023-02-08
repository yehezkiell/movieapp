package com.movieapp.authentication.repository

import com.google.gson.Gson
import com.movieapp.authentication.model.account.AccountDetail
import com.movieapp.authentication.model.account.SessionData
import com.movieapp.authentication.model.account.SessionRequestToken
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.getData
import com.tkpd.abstraction.network.MovieAPI
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val gson: Gson
) : AccountRepository {

    override suspend fun createNewToken(): Result<SessionRequestToken> {
        return getData(
            gson, apiCall = {
                movieAPI.requestNewToken()
            },
            SessionRequestToken::class.java
        )
    }

    override suspend fun createRequestTokenWithLogin(
        username: String,
        password: String,
        requestToken: String
    ): Result<SessionRequestToken> {
        return getData(
            gson, apiCall = {
                movieAPI.createRequestTokenWithLogin(username, password, requestToken)
            },
            SessionRequestToken::class.java
        )
    }

    override suspend fun createSessionWithToken(requestToken: String): Result<SessionData> {
        return getData(
            gson, apiCall = {
                movieAPI.createSessionWithRequestToken(requestToken)
            },
            SessionData::class.java
        )
    }

    override suspend fun getDetailAccount(sessionId: String): Result<AccountDetail> {
        return getData(
            gson, apiCall = {
                movieAPI.getDetailAccount(sessionId)
            },
            AccountDetail::class.java
        )
    }
}