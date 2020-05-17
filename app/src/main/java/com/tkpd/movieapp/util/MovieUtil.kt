package com.tkpd.movieapp.util

/**
 * Created by Yehezkiel on 17/05/20
 */

fun <T : Any?> Result<T?>.doSuccessOrFail(
    success: (Result.Success<T?>) -> Unit,
    fail: (Throwable) -> Unit
) {
    when (this) {
        is Result.Success -> {
            success.invoke(this)
        }
        is Result.Error -> {
            fail.invoke(this.throwable)
        }
    }
}

