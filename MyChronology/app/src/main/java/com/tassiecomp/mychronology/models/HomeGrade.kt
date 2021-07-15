package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName ="grades"
)
//dfasdf
data class HomeGrade(
    var title: String,
    var secondaryTitle: String,
    var weigthing:String,
    var sliderValue: Double? = 0.0,
    var min:Double?,
    var max:Double?

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}