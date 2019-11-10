package com.adarsh.newsappkotlin.api

import android.util.Log
import com.adarsh.newsappkotlin.constants.API_KEY
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun getRetrofitInstance(baseUrl: String): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->

                val queryUrl = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(queryUrl)
                    .build()

                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor(
                    HttpLoggingInterceptor.Logger {
                        Log.i("ApiInterface", ": $it")
                    }
                ).setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}