package com.example.testmodulotech.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T> : ViewModel() {

    private val _data: MutableLiveData<T> = MutableLiveData()
    val data: LiveData<T>
        get() = _data

    fun postData(data: T){
        _data.value = data
    }
}