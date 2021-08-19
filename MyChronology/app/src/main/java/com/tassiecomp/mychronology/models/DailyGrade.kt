package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(
    tableName ="dailyGrades"
)
data class DailyGrade(
    @PrimaryKey(autoGenerate = true)
    val title: String,
    val date: Long,
    val subjectScore:Double

){

}
