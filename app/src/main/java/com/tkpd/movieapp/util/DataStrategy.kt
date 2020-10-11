package com.tkpd.movieapp.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

/**
 * Created by Yehezkiel on 11/10/20
 */

fun <T> performDataStrategy(databaseQuery: () -> LiveData<Result<T?>>,
                            networkCall: suspend () -> Result<T>,
                            saveToDb: suspend (T?) -> Unit): LiveData<Result<T?>> =
    liveData(Dispatchers.IO) {
        emit(Result.Loading)
        val source = databaseQuery.invoke()
        emitSource(source)
        val responseStatus = networkCall.invoke()

        if (responseStatus is Result.Success) {
            //If the data in local and remote are equal, no need to update the db
            if ((source.value as Result.Success).data.hashCode() != responseStatus.data.hashCode()) {
                saveToDb(responseStatus.data)
                Log.e("asd", "masil")
            }
        } else if (responseStatus is Result.Error) {
            emit(Result.Error(responseStatus.throwable))
            if (source.value != null) {
                emitSource(source)
            }
        }
    }