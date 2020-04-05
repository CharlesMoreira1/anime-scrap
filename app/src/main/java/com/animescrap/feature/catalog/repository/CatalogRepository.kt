package com.animescrap.feature.catalog.repository

import com.animescrap.data.model.catalog.domain.CatalogDomain
import com.animescrap.data.model.catalog.entity.CatalogResponse
import com.animescrap.data.model.catalog.mapper.CatalogMapper
import com.animescrap.data.source.remote.api.ApiServiceSoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogRepository(private val apiServiceSoup: ApiServiceSoup) {

    private lateinit var listCatalogResponse: List<CatalogResponse>

    suspend fun getListAnime(url: String): List<CatalogDomain>{

        withContext(Dispatchers.IO) {
            listCatalogResponse = CatalogResponse()
                .addElements(apiServiceSoup.getListAnime(url))
                .sortedByDescending { it.year }
        }

        return CatalogMapper.transformEntityToDomain(listCatalogResponse)
    }
}