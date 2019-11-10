package com.adarsh.newsappkotlin.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface CustomWorkerFactory {

    fun create(context: Context, workerParameters: WorkerParameters): ListenableWorker
}