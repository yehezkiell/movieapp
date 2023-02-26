package com.movieapp.authentication.usecase

import com.movieapp.authentication.model.account.AccountDetail
import com.movieapp.authentication.repository.AccountRepository
import com.tkpd.abstraction.session.UserSession
import com.tkpd.abstraction.extension.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class GetAccountDetailUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userSession: UserSession
) {
    suspend operator fun invoke(): Flow<Result<AccountDetail>> {
        return flow {
            val sessionId = userSession.getSessionId().first()
            val accountDetail = accountRepository.getDetailAccount(sessionId)
            emit(accountDetail)
        }.catch {
            AccountDetail()
        }.flowOn(Dispatchers.IO)
    }
}