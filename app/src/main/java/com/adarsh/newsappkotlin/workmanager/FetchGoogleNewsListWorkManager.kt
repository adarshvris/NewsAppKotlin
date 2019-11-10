package com.adarsh.newsappkotlin.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.adarsh.newsappkotlin.api.NewsApiService
import com.adarsh.newsappkotlin.constants.GOOGLE_NEWS_LIST
import com.adarsh.newsappkotlin.constants.SOURCE
import com.adarsh.newsappkotlin.db.dao.NewsDao
import com.adarsh.newsappkotlin.di.CustomWorkerFactory
import com.adarsh.newsappkotlin.extension.convertToJsonString
import javax.inject.Inject

class FetchGoogleNewsListWorkManager @Inject constructor(
    private val context: Context,
    private val workerParameters: WorkerParameters,
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) :
    NewsWorker(context, workerParameters) {

    override suspend fun doWorker(): Result {

        val source = workerParameters.inputData.getString(SOURCE)

        source?.let { sourceVal ->
            val response = newsApiService.getGoogleNews(sourceVal)

            if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()!!.listOfGoogleNews?.let { newsDao.upsertAllGoogleDetails(it) }
                    return Result.success()
                }
            }

            return Result.failure()
        }

        return Result.failure()
    }
}

class FetchGoogleNewsListWorkFactory @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
): CustomWorkerFactory {
    override fun create(context: Context, workerParameters: WorkerParameters): ListenableWorker {
        return FetchGoogleNewsListWorkManager(context, workerParameters, newsApiService, newsDao)
    }

}