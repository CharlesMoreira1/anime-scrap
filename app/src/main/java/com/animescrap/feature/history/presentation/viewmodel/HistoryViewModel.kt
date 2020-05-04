package com.animescrap.feature.history.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.core.helper.Resource
import com.animescrap.data.model.history.domain.HistoryDomain
import com.animescrap.feature.history.repository.HistoryRepository

class HistoryViewModel(private val repository: HistoryRepository) : BaseViewModel() {

    private val mutableLiveDataListAnime = MutableLiveData<Resource<List<HistoryDomain>>>()

    val getLiveDataHistory
        get() = mutableLiveDataListAnime

    init {
        fetchHistory()
    }

    private fun fetchHistory() {
        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListAnime.success(repository.getList())
            },
            onError = {
                mutableLiveDataListAnime.error(it)
            })
    }

    fun refreshViewModel() {
        fetchHistory()
    }

}
