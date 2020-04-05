package com.animescrap.feature.home.repository

import com.animescrap.data.model.home.domain.NewEpisodeDomain
import com.animescrap.data.model.home.entity.NewEpisodeResponse
import com.animescrap.data.model.home.mapper.HomeMapper
import com.animescrap.data.source.remote.api.ApiServiceSoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val apiServiceSoup: ApiServiceSoup) {

    private lateinit var listNewEpisodeResponse: List<NewEpisodeResponse>

    suspend fun getListNewEpisode(url: String): List<NewEpisodeDomain>{
        withContext(Dispatchers.IO) {
            listNewEpisodeResponse = NewEpisodeResponse().addElements(apiServiceSoup.getListNewEpisode(url))
        }

        return HomeMapper.transformEntityToDomain(listNewEpisodeResponse)
    }
}