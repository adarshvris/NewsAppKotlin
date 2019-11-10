package com.adarsh.newsappkotlin.di

import com.adarsh.newsappkotlin.ui.news.google.GoogleNewsDetailFragment
import com.adarsh.newsappkotlin.ui.news.google.GoogleNewsListFragment
import com.adarsh.newsappkotlin.ui.news.NewsOptionFragment
import com.adarsh.newsappkotlin.ui.news.indian.IndianNewsDetailFragment
import com.adarsh.newsappkotlin.ui.news.indian.IndianNewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope(NewsOptionFragment::class)
    @ContributesAndroidInjector
    abstract fun getNewsOptionFragment(): NewsOptionFragment

    @FragmentScope(GoogleNewsListFragment::class)
    @ContributesAndroidInjector
    abstract fun getNewsListFragment(): GoogleNewsListFragment

    @FragmentScope(GoogleNewsDetailFragment::class)
    @ContributesAndroidInjector
    abstract fun getNewsDetailFragment(): GoogleNewsDetailFragment

    @FragmentScope(IndianNewsListFragment::class)
    @ContributesAndroidInjector
    abstract fun contributeIndianNewsListFragment(): IndianNewsListFragment

    @FragmentScope(IndianNewsDetailFragment::class)
    @ContributesAndroidInjector
    abstract fun contributeIndianNewsDetailFragment(): IndianNewsDetailFragment
}