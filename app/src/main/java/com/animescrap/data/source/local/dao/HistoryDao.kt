package com.animescrap.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.animescrap.data.model.history.entity.HistoryCatalogResponse
import com.animescrap.data.model.history.entity.HistoryResponse

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history h JOIN anime a ON a.title_encoded = h.titleEncoded ORDER BY h.date DESC")
    suspend fun getList(): List<HistoryCatalogResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyEntity: HistoryResponse)

    @Query("DELETE FROM history where titleEncoded = :id")
    suspend fun remove(id: String)
}