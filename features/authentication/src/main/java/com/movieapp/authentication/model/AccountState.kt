package com.movieapp.authentication.model

import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.Result

sealed class AccountState {
    object Detail : AccountState()
    object Loading : AccountState()
    data class FailLogin(val message: String) : AccountState()
}