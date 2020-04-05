package com.animescrap.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.animescrap.core.helper.Resource
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    protected fun <T> MutableLiveData<Resource<T>>.success(data: T?) {
        value = Resource.success(data)
    }

    protected fun <T> MutableLiveData<Resource<T>>.error(e: Exception?) {
        value = Resource.error(e)
    }

    protected fun <T> MutableLiveData<Resource<T>>.loading() {
        value = Resource.loading()
    }

    protected fun <T> CoroutineScope.launchWithCallback(onSuccess: suspend () -> T, onError: (Exception) -> Unit) {
        launch {
            try {
                onSuccess.invoke()
            } catch (e: Exception) {
                onError.invoke(e)
            }
        }
    }
}