package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName ="homeGrades"
)
//dfasdf
data class HomeGrade(
    @PrimaryKey var title: String,
    var description: String,
    var weigthing:String,
    var min:Int,
    var max:Int

) {

}