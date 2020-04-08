package com.animescrap.feature.detail.repository

import com.animescrap.data.model.detail.domain.DetailDomain
import com.animescrap.data.model.detail.entity.DetailResponse
import com.animescrap.data.model.detail.mapper.DetailMapper
import com.animescrap.data.source.remote.api.ApiServiceSoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRepository(private val apiServiceSoup: ApiServiceSoup) {

    private lateinit var detailResponse: DetailResponse

    suspend fun getDetail(url: String): DetailDomain{
        withContext(Dispatchers.IO) {
            detailResponse = DetailResponse().addElements(apiServiceSoup.getDetail(url))
        }

        return DetailMapper.transformEntityToDomain(detailResponse)
    }
}