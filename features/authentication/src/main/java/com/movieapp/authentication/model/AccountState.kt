package com.movieapp.authentication.model

sealed class AccountState {
    object Detail : AccountState()
    object Loading : AccountState()
    object Idle : AccountState()
    sealed class Error : AccountState() {
        class FailLogin(val message: String) : Error()
        object RequireFieldEmpty : Error()
    }
}