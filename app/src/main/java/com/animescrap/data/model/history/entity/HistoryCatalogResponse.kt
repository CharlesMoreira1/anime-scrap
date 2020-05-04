package com.animescrap.data.model.history.entity

import androidx.room.Embedded
import com.animescrap.data.model.catalog.entity.CatalogResponse

data class HistoryCatalogResponse (@Embedded val historyResponse: HistoryResponse, @Embedded val catalogResponse: CatalogResponse )