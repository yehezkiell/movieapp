package com.movieapp.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.authentication.model.AccountState
import com.movieapp.authentication.usecase.LoginUseCase
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.session.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userSession: UserSession
) : ViewModel() {

    private val _loginState: MutableStateFlow<AccountState> =
        MutableStateFlow(AccountState.Loading)
    val loginState: StateFlow<AccountState>
        get() = _loginState

    fun doLogin(userName: String, password: String) {
        if (userName.isEmpty()) {
            updateFieldEmpty()
            return
        }

        if (password.isEmpty()) {
            updateFieldEmpty()
            return
        }

        viewModelScope.launch {
            val loginData = loginUseCase.invoke(userName, password)

            loginData.collect { data ->
                when (data) {
                    is Result.Success -> {
                        _loginState.update {
                            AccountState.Detail
                        }

                        userSession.getSessionId().collect {
                            Log.e("asd", it)
                        }
                    }
                    is Result.Error -> {
                        _loginState.update {
                            AccountState.FailLogin(data.throwable.message ?: "")
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun updateFieldEmpty() {
        _loginState.update {
            AccountState.RequireFieldEmpty
        }
    }
}