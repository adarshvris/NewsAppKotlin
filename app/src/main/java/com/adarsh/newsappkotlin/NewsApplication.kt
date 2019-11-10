package com.adarsh.newsappkotlin

import android.app.Activity
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.adarsh.newsappkotlin.di.AppInjector
import com.adarsh.newsappkotlin.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

val newsApplicationInstance by lazy { NewsApplication.appInstance!! }

class NewsApplication : DaggerApplication(), HasActivityInjector {

    companion object {
        var appInstance: NewsApplication? = null
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().bind(this).build()

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        AppInjector.init(this)

        WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(workerFactory).build())
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = androidInjector
}