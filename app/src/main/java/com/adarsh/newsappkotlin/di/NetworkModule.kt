package com.adarsh.newsappkotlin.di

import com.adarsh.newsappkotlin.api.NewsApiService
import com.adarsh.newsappkotlin.api.RetrofitFactory
import com.adarsh.newsappkotlin.constants.BASE_URL
import com.adarsh.newsappkotlin.db.dao.NewsDao
import com.adarsh.newsappkotlin.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesNewsApiService(): NewsApiService =
        RetrofitFactory.getRetrofitInstance(BASE_URL).create(NewsApiService::class.java)

    @Singleton
    @Provides
    fun providesNewsRepository(newsDao: NewsDao, newsApiService: NewsApiService) =
        NewsRepository(newsDao, newsApiService)
}