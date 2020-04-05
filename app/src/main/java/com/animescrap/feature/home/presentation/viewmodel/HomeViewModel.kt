package com.animescrap.feature.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.core.constant.HOME_URL
import com.animescrap.core.helper.Resource
import com.animescrap.data.model.home.domain.NewEpisodeDomain
import com.animescrap.feature.home.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val mutableLiveDataListNewEpisode = MutableLiveData<Resource<List<NewEpisodeDomain>>>()

    init {
        fetchListNewEpisode()
    }

    val getLiveDataListNewEpisode: LiveData<Resource<List<NewEpisodeDomain>>>
        get() = mutableLiveDataListNewEpisode

    private fun fetchListNewEpisode() {
        mutableLiveDataListNewEpisode.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListNewEpisode.success(repository.getListNewEpisode(HOME_URL))
            },
            onError = {
                mutableLiveDataListNewEpisode.error(it)
            })
    }
}