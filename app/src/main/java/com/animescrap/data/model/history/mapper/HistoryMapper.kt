package com.animescrap.data.model.history.mapper

import com.animescrap.data.model.history.domain.HistoryDomain
import com.animescrap.data.model.history.entity.HistoryCatalogResponse

object HistoryMapper {
    fun transformEntityToDomain(listHistoryResponse: List<HistoryCatalogResponse>): List<HistoryDomain> {
        val listHistoryDomain = ArrayList<HistoryDomain>()

        listHistoryResponse.forEach {
            val historyDomain = HistoryDomain(
                title = it.catalogResponse.title,
                image = it.catalogResponse.image,
                url = it.catalogResponse.url
            )
            listHistoryDomain.add(historyDomain)
        }

        return listHistoryDomain
    }
}