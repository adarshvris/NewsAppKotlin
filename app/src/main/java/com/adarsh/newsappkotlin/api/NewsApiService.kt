package com.adarsh.newsappkotlin.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getGoogleNews(
        @Query("sources") sources: String
    ): Response<GoogleNewsResponse>

    @GET("top-headlines")
    suspend fun getIndianNews(
        @Query("country") country: String
    ): Response<IndianNewsResponse>
}