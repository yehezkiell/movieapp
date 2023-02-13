package com.movieapp.authentication.model

enum class ResourceState { LOADING, ERROR, SUCCESS, IDLE }

data class AccountState(
    val idle: Boolean = true,
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean? = null
) {
    val state: ResourceState
        get() {
            return if (loading) ResourceState.LOADING
            else if (error != null) ResourceState.ERROR
            else if (success != null) ResourceState.SUCCESS
            else ResourceState.IDLE
        }
}