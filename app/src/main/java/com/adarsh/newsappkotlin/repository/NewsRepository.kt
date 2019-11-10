package com.adarsh.newsappkotlin.repository

import android.util.Log
import com.adarsh.newsappkotlin.api.*
import com.adarsh.newsappkotlin.db.dao.NewsDao
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApiService: NewsApiService
) {

    suspend fun getGoogleNewsList(source: String, isOnline: Boolean) =
        object : NetworkBoundResource<List<GoogleNewsDetail>, GoogleNewsResponse>() {
            override suspend fun loadFromDb(): List<GoogleNewsDetail> =
                newsDao.getAllGoogleDetails()

            override fun shouldFetchFromNetwork(data: List<GoogleNewsDetail>?): Boolean = isOnline

            override suspend fun createCall(): Response<GoogleNewsResponse> =
                newsApiService.getGoogleNews(source)

            override suspend fun saveCallResult(data: GoogleNewsResponse?) {
                newsDao.clearAllGoogleDetails()
                data?.listOfGoogleNews?.let { newsDao.upsertAllGoogleDetails(it) }
            }
        }.asLiveData()


    suspend fun getIndianNews(country: String, isOnline: Boolean) =
        object : NetworkBoundResource<List<IndianNewsDetail>, IndianNewsResponse>() {
            override suspend fun loadFromDb(): List<IndianNewsDetail> =
                newsDao.getAllIndianNewsDetails()

            override fun shouldFetchFromNetwork(data: List<IndianNewsDetail>?): Boolean = isOnline

            override suspend fun createCall(): Response<IndianNewsResponse> =
                newsApiService.getIndianNews(country)

            override suspend fun saveCallResult(data: IndianNewsResponse?) {
                newsDao.clearAllIndianDetails()
                data?.listOfIndianNewsDetail?.let { newsDao.upsertAllIndianNewsDetails(it) }
            }

        }.asLiveData()
}