package com.tassiecomp.mychronology.models

import android.os.Parcelable
import androidx.room.ColumnInfo
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
    val title: String, //가져옴 parse
    val createdDate: String, //오늘 날짜 가져옴
    val checkBox:Boolean, //처음은 무조건 false
    val textData: String, // edit text에서 가져오기
    val priority: Boolean, // 처음은 무저건 false
    var color: String //parse에서 가져오기

    ): Parcelable {

}


