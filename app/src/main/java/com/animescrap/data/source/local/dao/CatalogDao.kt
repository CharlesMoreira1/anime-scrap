package com.animescrap.data.source.local.dao

import androidx.room.*
import com.animescrap.data.model.catalog.entity.CatalogResponse

@Dao
interface CatalogDao {

    @Query("SELECT * FROM anime")
    suspend fun getListAnime(): List<CatalogResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListAnime(listCatalogResponse: List<CatalogResponse>)

    @Query("DELETE FROM anime")
    suspend fun removeListAnime()
}