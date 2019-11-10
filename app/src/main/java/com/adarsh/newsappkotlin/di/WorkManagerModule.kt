package com.adarsh.newsappkotlin.di

import androidx.work.WorkerFactory
import com.adarsh.newsappkotlin.workmanager.FetchGoogleNewsListWorkFactory
import com.adarsh.newsappkotlin.workmanager.FetchGoogleNewsListWorkManager
import com.adarsh.newsappkotlin.workmanager.FetchIndianNewsListWorkFactory
import com.adarsh.newsappkotlin.workmanager.FetchIndianNewsListWorkManager
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkManagerModule {

    @Binds
    abstract fun bindsWorkManagerWorkerFactory(workManagerWorkerFactory: WorkManagerWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(FetchGoogleNewsListWorkManager::class)
    abstract fun bindFetchGoogleNewsWorkManager(classesListFactory: FetchGoogleNewsListWorkFactory): CustomWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(FetchIndianNewsListWorkManager::class)
    abstract fun bindFetchIndianNewsWorkManager(classesListFactory: FetchIndianNewsListWorkFactory): CustomWorkerFactory
}