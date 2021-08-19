package com.tassiecomp.mychronology.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName ="homeGrades"
)
//dfasdf
data class HomeGrade(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var title: String,
    var description: String,
    var weigthing:String,
    var min:Int,
    var max:Int,
    var color: String

):Parcelable {

}