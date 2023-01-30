package com.movieapp.authentication.repository

import com.tkpd.abstraction.data.account.AccountDetail
import com.tkpd.abstraction.data.account.SessionData
import com.tkpd.abstraction.data.account.SessionRequestToken
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.stateCall
import com.tkpd.abstraction.network.MovieAPI
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(private val movieAPI: MovieAPI) :
    AccountRepository {

    override suspend fun createNewToken(): Result<SessionRequestToken> {
        val data = movieAPI.requestNewToken()
        return stateCall {
            data
        }
    }

    override suspend fun createRequestTokenWithLogin(
        username: String,
        password: String,
        requestToken: String
    ): Result<SessionRequestToken> {
        val data = movieAPI.createRequestTokenWithLogin(username, password, requestToken)
        return stateCall {
            data
        }
    }

    override suspend fun createSessionWithToken(requestToken: String): Result<SessionData> {
        val data = movieAPI.createSessionWithRequestToken(requestToken)
        return stateCall {
            data
        }
    }

    override suspend fun getDetailAccount(sessionId: String): Result<AccountDetail> {
        val data = movieAPI.getDetailAccount(sessionId)
        return stateCall {
            data
        }
    }
}