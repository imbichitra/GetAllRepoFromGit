package com.bichi.getallrepofromgit.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    var baseUrl = "https://api.github.com/users/"
    fun getInstance() : Retrofit? {
        if (retrofit == null) {
            val gson = GsonBuilder().serializeNulls().create()
            retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //.client(okHttpClient)
                    .build()
        }
        return retrofit
    }
}