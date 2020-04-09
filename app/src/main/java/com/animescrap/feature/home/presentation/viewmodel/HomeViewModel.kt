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
    private val listNewEpisodeCopy = mutableListOf<NewEpisodeDomain>()
    var currentPage = 1
    var releasedLoad: Boolean = true

    init {
        fetchListNewEpisode()
    }

    val getLiveDataListNewEpisode: LiveData<Resource<List<NewEpisodeDomain>>>
        get() = mutableLiveDataListNewEpisode

    private fun fetchListNewEpisode(page: Int = 1) {
        mutableLiveDataListNewEpisode.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                listNewEpisodeCopy.addAll(repository.getListNewEpisode(HOME_URL.plus(page)))
                if (listNewEpisodeCopy.isNotEmpty()) {
                    mutableLiveDataListNewEpisode.success(listNewEpisodeCopy)
                    releasedLoad = true
                }
            },
            onError = {
                mutableLiveDataListNewEpisode.error(it)
            })
    }

    fun refreshViewModel() {
        currentPage = 1
        fetchListNewEpisode()
    }

    fun nextPage() {
        fetchListNewEpisode(++currentPage)
        releasedLoad = false
    }

    fun backPreviousPage() {
        fetchListNewEpisode(currentPage)
    }
}