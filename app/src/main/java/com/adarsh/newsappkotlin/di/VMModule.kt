package com.adarsh.newsappkotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adarsh.newsappkotlin.ui.vm.NewsVM
import com.adarsh.newsappkotlin.ui.vm.OptionSharedVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {

    @Binds
    abstract fun bindsViewModelProvider(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(OptionSharedVM::class)
    abstract fun bindsOptionSharedVM(optionSharedVM: OptionSharedVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsVM::class)
    abstract fun bindsNewsVM(newsVM: NewsVM): ViewModel

}