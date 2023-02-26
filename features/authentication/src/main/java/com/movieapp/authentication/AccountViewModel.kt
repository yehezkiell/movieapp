package com.movieapp.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.authentication.model.AccountState
import com.movieapp.authentication.usecase.GetAccountDetailUseCase
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
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
    val userSession: UserSession
) : ViewModel() {

    private val _loginState: MutableStateFlow<AccountState> =
        MutableStateFlow(AccountState())
    val loginState: StateFlow<AccountState>
        get() = _loginState

    private val _isLoggedIn: MutableStateFlow<Boolean> =
        MutableStateFlow(userSession.getSessionIdBlocking().isNotEmpty())
    val isLoggedIn: StateFlow<Boolean>
        get() = _isLoggedIn

    init {
        loadAccountDetailWhenLoggedIn()
    }

    private fun loadAccountDetailWhenLoggedIn() {
        viewModelScope.launch {
            if (isLoggedIn.value) {
                val result = getAccountDetailUseCase.invoke()
                result.collect {
                    when (it) {
                        is Result.Success -> {
                            _loginState.value = AccountState(data = it.data)
                        }
                        is Result.Error -> {
                            _loginState.value = AccountState(error = it.throwable.message)
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            userSession.clearSessionId()
            _isLoggedIn.update {
                false
            }
        }
    }

    fun doLogin(userName: String, password: String) {
        _loginState.value = AccountState(loading = true)

        if (userName.isEmpty()) {
            _loginState.value = AccountState(error = "Username Shouldn't Empty")
            return
        }

        if (password.isEmpty()) {
            _loginState.value = AccountState(error = "Password Shouldn't Empty")
            return
        }

        viewModelScope.launch {
            val loginData = loginUseCase.invoke(userName, password)

            loginData.collect { data ->
                when (data) {
                    is Result.Success -> {
                        _loginState.value = AccountState(data = data.data)
                        _isLoggedIn.update {
                            true
                        }
                    }
                    is Result.Error -> {
                        _loginState.value = AccountState(error = data.throwable.message)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    fun resetErrorMessage() {
        _loginState.tryEmit(AccountState())
    }
}