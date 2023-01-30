package com.movieapp.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.authentication.model.AccountState
import com.movieapp.authentication.usecase.AccountUseCase
import com.tkpd.abstraction.extension.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(val accountUseCase: AccountUseCase) : ViewModel() {

    private val _loginState: MutableStateFlow<AccountState> =
        MutableStateFlow(AccountState.Loading)
    val loginState: StateFlow<AccountState>
        get() = _loginState

    fun doLogin(userName: String, password: String) {
        viewModelScope.launch {
            val loginData = accountUseCase.invoke(userName, password)

            loginData.collect { data ->
                when (data) {
                    is Result.Success -> {
                        _loginState.update {
                            AccountState.Detail
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
}