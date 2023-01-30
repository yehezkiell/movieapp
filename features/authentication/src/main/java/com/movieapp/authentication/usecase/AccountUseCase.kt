package com.movieapp.authentication.usecase

import com.movieapp.authentication.repository.AccountRepository
import com.tkpd.abstraction.extension.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * TMBD v3 does not has bearer token at the moment. Then if we need to verify authorization, we need
 * session_id. To get session_id we need to do :
 * 1. get initial request_token from api_key
 * 2. get new request_token from username and password along with initial request_id
 * 3. get session_id from step 2 request_token
 */
class AccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend operator fun invoke(userName: String, password: String): Flow<Result<Boolean>> {
        return flow {
            val newToken = accountRepository.createNewToken()

            if (newToken is Result.Error) {
                emit(Result.Error(Throwable("Error Get New Token")))
                return@flow
            }

            val loginData =
                accountRepository.createRequestTokenWithLogin(
                    userName,
                    password,
                    (newToken as Result.Success).data.requestToken
                )

            if (loginData is Result.Error) {
                emit(Result.Error(loginData.throwable))
                return@flow
            }

            if (loginData is Result.Success) {
                if (!loginData.data.success) {
                    emit(Result.Error(Throwable("Fail")))
                } else {
                    val sessionId =
                        accountRepository.createSessionWithToken(loginData.data.requestToken)
                    emit(Result.Success(true))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAccountDetail() {

    }
}