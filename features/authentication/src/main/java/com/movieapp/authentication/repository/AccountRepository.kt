package com.movieapp.authentication.repository

import com.tkpd.abstraction.data.account.AccountDetail
import com.tkpd.abstraction.data.account.SessionData
import com.tkpd.abstraction.data.account.SessionRequestToken
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