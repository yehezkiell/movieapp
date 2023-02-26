package com.tkpd.abstraction.extension

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tkpd.abstraction.R
import com.tkpd.abstraction.constant.MovieConstant.MOVIE_ORIGINAL_IMAGE
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Yehezkiel on 17/05/20
 */

fun <T : Any?> Result<T?>.doSuccessOrFail(
    success: (Result.Success<T?>) -> Unit,
    fail: (Throwable) -> Unit,
    loading: () -> Unit,
    hideLoading: () -> Unit
) {
    when (this) {
        is Result.Success -> {
            hideLoading.invoke()
            success.invoke(this)
        }
        is Result.Error -> {
            hideLoading.invoke()
            fail.invoke(this.throwable)
        }
        is Result.Loading -> {
            loading.invoke()
        }
    }
}

suspend fun <T> getData(
    gson: Gson,
    apiCall: suspend () -> Response<JsonObject>,
    clazz: Class<T>
): Result<T> {
    return try {
        val data: Response<JsonObject> = apiCall.invoke()
        return data.toResult(gson, clazz)
    } catch (e: Exception) {
        Log.e("errr","ke throw")
        throw Throwable(e.message)
    }
}

private fun <T, K> Response<T>.toResult(gson: Gson, type: Class<K>): Result<K> {
    return if (isSuccessful) {
        val rawString = body().toString()
        return Result.Success(gson.fromJson(rawString, type))
    } else {
        val jsonObject = errorBody()?.string()?.toJsonObject()
        val statusMessage = jsonObject?.optString(
            "status_message",
            "Connection Error"
        ) ?: "Connection Error"

        Result.Error(Throwable(statusMessage))
    }
}

fun String.toJsonObject(): JSONObject {
    return JSONObject(this)
}

fun String?.createImageUrl(): String {
    if (this == null) return ""
    return MOVIE_ORIGINAL_IMAGE + this
}

fun ImageView.loadImageRounded(imageUrl: String, roundedValue: Int = 24) {
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_image_black_24dp))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_image_black_24dp))
        .transform(RoundedCorners(roundedValue))
        .into(this)
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_image_black_24dp))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_image_black_24dp))
        .into(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

