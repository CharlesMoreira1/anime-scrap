package com.animescrap.core.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.animescrap.core.helper.Resource.*

sealed class Resource<T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val throwable: Throwable?) : Resource<T>()
    class Loading<T>: Resource<T>()

    companion object {
        fun <T> success(data: T?): Resource<T> = Success(data)
        fun <T> error(throwable: Throwable?): Resource<T> = Error(throwable)
        fun <T> loading(): Resource<T> = Loading()
    }
}

fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onLoading: () -> Unit = {}) {

    observe(owner, Observer { resource ->
        when (resource) {
            is Success -> resource.data?.let { onSuccess.invoke(it) }
            is Error -> resource.throwable?.let { onError.invoke(it) }
            is Loading -> onLoading.invoke()
        }
    })
}