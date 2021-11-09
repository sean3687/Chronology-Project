package com.tassiecomp.mychronology.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName ="dailyGrades"
)
data class DailyGrade(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val createdDate: String,
    val checkBox:Boolean,
    val textData: String,
    val priority: Boolean

    ): Parcelable {

}


