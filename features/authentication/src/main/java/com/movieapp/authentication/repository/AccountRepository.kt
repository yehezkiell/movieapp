package com.movieapp.authentication.repository

import com.movieapp.authentication.model.account.AccountDetail
import com.movieapp.authentication.model.account.SessionData
import com.movieapp.authentication.model.account.SessionRequestToken
import com.tkpd.abstraction.extension.Result

interface AccountRepository {
    suspend fun createNewToken(): Result<SessionRequestToken>
    suspend fun createRequestTokenWithLogin(
        username: String,
        password: String,
        requestToken: String
    ): Result<SessionRequestToken>

    suspend fun createSessionWithToken(requestToken: String): Result<SessionData>
    suspend fun getDetailAccount(sessionId: String): Result<AccountDetail>
}