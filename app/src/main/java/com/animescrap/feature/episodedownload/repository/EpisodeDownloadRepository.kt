package com.animescrap.feature.episodedownload.repository

import com.animescrap.data.source.remote.api.ApiServiceSoup
import com.animescrap.feature.episodedownload.helper.transformStringEpisodeInNumber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodeDownloadRepository(private val apiServiceSoup: ApiServiceSoup) {

    private var numberEpisode: Int? = 0

    suspend fun getNumberEpisode(url: String): Int?{
        withContext(Dispatchers.IO) {
            try {
                numberEpisode = apiServiceSoup.getEpisodeDownload(url).text().transformStringEpisodeInNumber()
            } catch (t: Throwable){
                null
            }
        }

        return numberEpisode
    }
}