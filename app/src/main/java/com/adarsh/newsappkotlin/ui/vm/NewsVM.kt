package com.adarsh.newsappkotlin.ui.vm

import androidx.lifecycle.ViewModel
import com.adarsh.newsappkotlin.repository.NewsRepository
import com.adarsh.newsappkotlin.util.startOneTimeWorkManager
import com.adarsh.newsappkotlin.workmanager.FetchGoogleNewsListWorkManager
import com.adarsh.newsappkotlin.workmanager.FetchIndianNewsListWorkManager
import javax.inject.Inject

class NewsVM @Inject constructor(
    private val googleNewsRepository: NewsRepository
) : ViewModel() {

    suspend fun getGoogleNewsList(source: String, isOnline: Boolean) =
        googleNewsRepository.getGoogleNewsList(source, isOnline)


    suspend fun getIndianNewsList(country: String, isOnline: Boolean) =
        googleNewsRepository.getIndianNews(country, isOnline)

    fun getGoogleNewsListUsingWorkManager(uniqueName: String, data: Map<String, Any>) =
        startOneTimeWorkManager<FetchGoogleNewsListWorkManager>(uniqueName, data)

    fun getIndianNewsListUsingWorkManager(uniqueName: String, data: Map<String, Any>) =
        startOneTimeWorkManager<FetchIndianNewsListWorkManager>(uniqueName, data)

}