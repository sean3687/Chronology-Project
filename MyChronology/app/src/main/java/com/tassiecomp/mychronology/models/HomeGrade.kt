package com.tassiecomp.mychronology.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName ="grades"
)
data class HomeGrade(
    var title: String,
    var secondaryTitle: String,
    var weigthing:String,
    var sliderValue: Double? = 0.0

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}