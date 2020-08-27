package com.anledev.logrequest.networks

import com.anledev.logrequest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("todos/1")
    fun getDemo(): Call<Any>

    companion object {
        operator fun invoke(): ApiService {
            val loggingInterceptor = LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Logger.Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}