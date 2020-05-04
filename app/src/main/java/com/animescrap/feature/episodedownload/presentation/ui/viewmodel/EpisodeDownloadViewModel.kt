package com.animescrap.feature.episodedownload.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.data.model.history.entity.HistoryResponse
import com.animescrap.feature.history.repository.HistoryRepository

class EpisodeDownloadViewModel(private val repository: HistoryRepository) : BaseViewModel() {

    fun addAnimeToHistory(history: HistoryResponse) {
        viewModelScope.launchWithCallback(
            onSuccess = {
                repository.insert(history)
            },
            onError = {

            })
    }
}