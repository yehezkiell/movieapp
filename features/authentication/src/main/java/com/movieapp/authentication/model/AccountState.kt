package com.movieapp.authentication.model

sealed class AccountState {
    object Detail : AccountState()
    object Loading : AccountState()
    data class FailLogin(val message: String) : AccountState()
    object RequireFieldEmpty : AccountState()
}