package com.animescrap.feature.catalog.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.core.constant.CATALOG_URL
import com.animescrap.core.helper.Resource
import com.animescrap.data.model.catalog.domain.CatalogDomain
import com.animescrap.feature.catalog.repository.CatalogRepository

class CatalogViewModel(private val repository: CatalogRepository) : BaseViewModel() {

    private val mutableLiveDataListAnime = MutableLiveData<Resource<List<CatalogDomain>>>()

    init {
        fetchListAnime()
    }

    val getLiveDataListAnime: LiveData<Resource<List<CatalogDomain>>>
        get() = mutableLiveDataListAnime

    private fun fetchListAnime() {
        mutableLiveDataListAnime.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListAnime.success(repository.getListAnime(CATALOG_URL))
            },
            onError = {
                mutableLiveDataListAnime.error(it)
            })
    }
}