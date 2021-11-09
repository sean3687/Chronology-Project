package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "dailyFts")
@Fts4(contentEntity = DailyGrade::class)
data class DailyGradeFTS(
    val createdDate: String,
    val title:String,
    val priority: Boolean
)
