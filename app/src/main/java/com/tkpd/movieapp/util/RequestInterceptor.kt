package com.tkpd.movieapp.util

import com.tkpd.movieapp.BuildConfig
import com.tkpd.movieapp.constant.MovieConstant
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Yehezkiel on 10/05/20
 */

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(MovieConstant.API_KEY_PARAM, BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}