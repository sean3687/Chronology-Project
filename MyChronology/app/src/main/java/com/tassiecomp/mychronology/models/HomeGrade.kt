package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName ="grades"
)
data class HomeGrade(
    @PrimaryKey(autoGenerate = true)
    var date:Int? = 0,
    var title: String,
    var secondaryTitle: String,
    var weigthing:String,
    var sliderValue: Double

) {

}