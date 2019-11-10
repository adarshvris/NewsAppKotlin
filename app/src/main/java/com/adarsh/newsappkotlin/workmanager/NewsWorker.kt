package com.adarsh.newsappkotlin.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

abstract class NewsWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    abstract suspend fun doWorker(): Result

    override suspend fun doWork(): Result {
        return doWorker()
    }
}