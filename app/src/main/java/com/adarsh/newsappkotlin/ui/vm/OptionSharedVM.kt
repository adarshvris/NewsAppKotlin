package com.adarsh.newsappkotlin.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class OptionSharedVM @Inject constructor() : ViewModel() {

    private val option = MutableLiveData<String>()

    fun setOption(optionSelected: String) {
        option.value = optionSelected
    }

    fun getOption(): MutableLiveData<String> = option
}