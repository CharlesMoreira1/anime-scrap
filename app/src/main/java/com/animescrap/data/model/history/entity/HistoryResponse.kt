package com.animescrap.data.model.history.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history")
data class HistoryResponse(
    @PrimaryKey var titleEncoded: String,
    var lastEpisode: Int?,
    var currentTime: String,
    var date: Long = Calendar.getInstance().timeInMillis)

