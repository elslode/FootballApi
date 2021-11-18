package com.elsloude.clientbanktest.data

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class Constants {
    companion object {
        const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor{chain ->  chain.request().newBuilder()
                .addHeader("x-rapidapi-key", "38d6a3f81cmsh9b03bc03de94120p1ac27cjsn0b61aacd9236")
                .build()
                .let(chain::proceed) }
            .build()
    }
}