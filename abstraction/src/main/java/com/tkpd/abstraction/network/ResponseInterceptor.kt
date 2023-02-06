package com.tkpd.abstraction.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject


class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)

        val stringJson = response.body!!.string()
        val jsonObject = JSONObject(stringJson)
        val contentType = response.body!!.contentType()
        val body: ResponseBody = ResponseBody.create(contentType, jsonObject.toString())

        Log.e("asd", jsonObject.toString())
        return response.newBuilder().body(body).build();
    }
}