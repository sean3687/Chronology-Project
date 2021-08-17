package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(
    tableName ="dailyGrades"
)
data class DailyGrade(
    @PrimaryKey var title: String,
    var date: Long,
    var subjectScore:Double

){

}
