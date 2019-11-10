package com.adarsh.newsappkotlin.di

import android.app.Application
import com.adarsh.newsappkotlin.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        WorkManagerModule::class]
)
interface AppComponent : AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bind(application: Application): Builder

        fun build(): AppComponent
    }

}