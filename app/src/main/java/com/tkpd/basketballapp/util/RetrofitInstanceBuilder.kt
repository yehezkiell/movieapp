package com.tkpd.basketballapp.util

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yehezkiel on 10/05/20
 */
object RetrofitInstanceBuilder {

    val retrofitInstance: BasketballAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .client(okHttpClientInstance)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(BasketballAPI::class.java)
    }

    private val okHttpClientInstance: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        client.build()
    }
}