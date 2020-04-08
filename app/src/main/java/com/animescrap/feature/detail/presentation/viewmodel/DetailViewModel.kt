package com.animescrap.feature.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.core.helper.Resource
import com.animescrap.data.model.detail.domain.DetailDomain
import com.animescrap.feature.detail.repository.DetailRepository

class DetailViewModel(private val repository: DetailRepository) : BaseViewModel() {

    private val mutableLiveDataDetail = MutableLiveData<Resource<DetailDomain>>()

    val getLiveDataDetail: LiveData<Resource<DetailDomain>>
        get() = mutableLiveDataDetail

    fun fetchDetail(url: String) {
        mutableLiveDataDetail.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataDetail.success(repository.getDetail(url))
            },
            onError = {
                mutableLiveDataDetail.error(it)
            })
    }
}