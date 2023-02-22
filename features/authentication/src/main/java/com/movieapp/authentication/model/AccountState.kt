package com.movieapp.authentication.model

import com.movieapp.authentication.model.account.AccountDetail

enum class ResourceState { LOADING, ERROR, SUCCESS, IDLE }

data class AccountState(
    val idle: Boolean = true,
    val loading: Boolean = false,
    val error: String? = null,
    val data: AccountDetail = AccountDetail(),
    val isLoggedIn: Boolean = false
) {
    val state: ResourceState
        get() {
            return if (loading) ResourceState.LOADING
            else if (error != null) ResourceState.ERROR
            else if (idle != null) ResourceState.IDLE
            else ResourceState.SUCCESS
        }
}