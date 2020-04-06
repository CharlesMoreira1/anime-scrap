package com.animescrap.feature.catalog.repository

import com.animescrap.core.base.BaseRepository
import com.animescrap.data.model.catalog.domain.CatalogDomain
import com.animescrap.data.model.catalog.entity.CatalogResponse
import com.animescrap.data.model.catalog.mapper.CatalogMapper
import com.animescrap.data.source.local.dao.CatalogDao
import com.animescrap.data.source.remote.api.ApiServiceSoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogRepository(private val apiServiceSoup: ApiServiceSoup, private val catalogDao: CatalogDao): BaseRepository() {

    private lateinit var listCatalogResponse: List<CatalogResponse>

    suspend fun getListAnime(url: String, forceRefresh: Boolean): List<CatalogDomain>{
        return networkBoundResource(
            makeApiCall = {
                withContext(Dispatchers.IO) {
                    listCatalogResponse = CatalogResponse()
                        .addElements(apiServiceSoup.getListAnime(url))
                        .sortedByDescending { it.year }
                }
                listCatalogResponse
            },
            shouldFetch = {
                catalogDao.getListAnime().isNullOrEmpty() || forceRefresh
            },
            saveCallResult = {
                catalogDao.removeListAnime()
                catalogDao.insertListAnime(it)
            },
            loadFromDb = {
                catalogDao.getListAnime()
            }
        ).transform { CatalogMapper.transformEntityToDomain(it) }!!
    }
}