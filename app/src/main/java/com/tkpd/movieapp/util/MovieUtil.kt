package com.tkpd.movieapp.util

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant.MOVIE_ORIGINAL_IMAGE
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

suspend fun <T : Any> stateCall(call: suspend () -> Response<T>): Result<T> {
    return if (call.invoke().isSuccessful) {
        Result.Success(call.invoke().body()!!)
    } else {
        Result.Error(Throwable(call.invoke().message()))
    }
}

fun String.createImageUrl(): String {
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

inline fun <reified T> String.fromJson() : T {
    return Gson().fromJson(this, object : TypeToken<T?>() {}.type)
}

fun <T : Any> T.toJson(): String {
    return Gson().toJson(this)
}

