package com.tkpd.movieapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

/**
 * Created by Yehezkiel on 11/10/20
 */

fun <T> performDataStrategy(databaseQuery: suspend () -> LiveData<Result<T?>>,
                            networkCall: suspend () -> Result<T>,
                            saveCallResult: suspend (T?) -> Unit): LiveData<Result<T?>> =
    liveData(Dispatchers.IO) {
        emit(Result.Loading)
        val source = databaseQuery.invoke()
        val responseStatus = networkCall.invoke()

        emitSource(source)

        if (responseStatus is Result.Success) {
            saveCallResult(responseStatus.data)
        } else if (responseStatus is Result.Error) {
            emit(Result.Error(responseStatus.throwable))
            if (source.value != null) {
                emitSource(source)
            }
        }
    }