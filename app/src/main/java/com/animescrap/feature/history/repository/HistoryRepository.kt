package com.animescrap.feature.history.repository

import com.animescrap.core.base.BaseRepository
import com.animescrap.data.model.history.domain.HistoryDomain
import com.animescrap.data.model.history.entity.HistoryResponse
import com.animescrap.data.model.history.mapper.HistoryMapper
import com.animescrap.data.source.local.dao.HistoryDao

class HistoryRepository(private val historyDao: HistoryDao): BaseRepository() {

    suspend fun getList(): List<HistoryDomain>{
        val animesInHistory = historyDao.getList()
        return HistoryMapper.transformEntityToDomain(animesInHistory)
    }

    suspend fun insert(history: HistoryResponse) {
        historyDao.remove(history.titleEncoded)
        historyDao.insert(history)
    }

}