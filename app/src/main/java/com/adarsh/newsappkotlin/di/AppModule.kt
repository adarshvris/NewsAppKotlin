package com.adarsh.newsappkotlin.di

import android.app.Application
import com.adarsh.newsappkotlin.db.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(application: Application) = NewsDatabase.invoke(application)

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase) = newsDatabase.getNewsDao()

}