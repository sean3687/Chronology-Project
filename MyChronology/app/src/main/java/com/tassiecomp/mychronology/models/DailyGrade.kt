package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(
    tableName ="dailyGrades"
)
data class DailyGrade(
    @PrimaryKey
    val date: Long,
    val title: String,
    val subjectScore:Double

){

}
