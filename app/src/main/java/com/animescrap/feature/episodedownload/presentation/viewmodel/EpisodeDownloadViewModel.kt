package com.animescrap.feature.episodedownload.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.animescrap.core.base.BaseViewModel
import com.animescrap.data.model.history.entity.HistoryResponse
import com.animescrap.feature.episodedownload.repository.EpisodeDownloadRepository
import com.animescrap.feature.history.repository.HistoryRepository

class EpisodeDownloadViewModel(private val historyRepository: HistoryRepository,
    private val episodeDownloadRepository: EpisodeDownloadRepository) : BaseViewModel() {

    fun addAnimeToHistory(url: String, titleEncoded: String) {
        viewModelScope.launchWithCallback(
            onSuccess = {
                val numberEpisode = episodeDownloadRepository.getNumberEpisode(url)
                historyRepository.insert(HistoryResponse(titleEncoded, numberEpisode, ""))
            },
            onError = {

            })
    }
}